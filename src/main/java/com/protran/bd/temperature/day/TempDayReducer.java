package com.protran.bd.temperature.day;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class TempDayReducer extends Reducer<Text, FloatWritable, Text, NullWritable> {

    public NullWritable nullWritable = NullWritable.get();
    double sum;
    double counter;

    @Override
    protected void reduce(Text key, Iterable<FloatWritable> floatTypes, Context context)
            throws IOException, InterruptedException {
        counter = 0;
        sum = 0;
        for (FloatWritable f : floatTypes) {
            sum += f.get();
            counter++;
        }
        //   String result = key.toString().substring(3,6) + key.toString().substring(0,3) + key.toString().substring(6);
        context.write(new Text("Day " + key + " avg_temp " + sum / counter), nullWritable);
    }

}