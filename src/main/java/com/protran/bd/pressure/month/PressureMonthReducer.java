package com.protran.bd.pressure.month;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class PressureMonthReducer extends Reducer<Text, FloatWritable, Text, NullWritable> {
    Map<String, Double> hm = new LinkedHashMap<>();
    public NullWritable nullWritable = NullWritable.get();
    double sum;
    int counter;


    @Override
    protected void reduce(Text key, Iterable<FloatWritable> floatTypes, Context context)
            throws IOException, InterruptedException {
        counter = 0;
        sum = 0;
        for (FloatWritable f : floatTypes) {
            sum += f.get();
            counter++;
        }

        hm.put(key.toString(), sum / counter);
       /* System.out.println(hm);
        Thread.sleep(3000);*/
        Optional<Map.Entry<String, Double>> first = hm.entrySet().stream().findFirst();
        String date = first.get().getKey();
        YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(date.substring(6, 10)), Integer.parseInt(date.substring(0, 2)));
        int daysInMonth = yearMonthObject.lengthOfMonth();
       /* System.out.println(key);
        System.out.println(yearMonthObject);
        System.out.println(daysInMonth);
        Thread.sleep(300);*/
        if (hm.size() == daysInMonth) {
            ArrayList<Double> listOfValues = new ArrayList<>(hm.values());
            double res = listOfValues.stream().mapToDouble(value -> value).average().orElse(0);
           /* Optional<Map.Entry<String, Double>> first1 = hm.entrySet().stream().findFirst();
            List<Map.Entry<String,Double>> entryList =
                    new ArrayList<>(hm.entrySet());
            Map.Entry<String, Double> lastEntry =
                    entryList.get(entryList.size()-1);*/
            String str = yearMonthObject.toString().replaceAll("-", "/");
            context.write(new Text("Month: " + str + " avg_pressure: " + res), nullWritable);
            hm.clear();
        }
    }
}
