package com.protran.bd.pressure.week;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.*;

public class PressureWeekReducer extends Reducer<Text, FloatWritable, Text, NullWritable> {
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
        if (hm.size() == 7) {
            ArrayList<Double> listOfValues = new ArrayList<>(hm.values());
            double res = listOfValues.stream().mapToDouble(value -> value).average().orElse(0);
            Optional<Map.Entry<String, Double>> first = hm.entrySet().stream().findFirst();
            List<Map.Entry<String,Double>> entryList =
                    new ArrayList<>(hm.entrySet());
            Map.Entry<String, Double> lastEntry =
                    entryList.get(entryList.size()-1);
            context.write(new Text("Week:" + " (" + first.get().getKey() + "-" + lastEntry.getKey() +")" +"; avg_pressure: " + res), nullWritable);
            hm.clear();
        }
    }
}

