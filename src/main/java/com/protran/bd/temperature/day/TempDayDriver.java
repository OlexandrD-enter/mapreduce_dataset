package com.protran.bd.temperature.day;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.time.LocalDateTime;

public class TempDayDriver extends Configured implements Tool {
    public int run(String[] arg0) {
        try {
            FileSystem fs = FileSystem.get(getConf());

            Job job = Job.getInstance(getConf());
            job.setJarByClass(getClass());

            job.setMapperClass(TempDayMapper.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(FloatWritable.class);

            job.setReducerClass(TempDayReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(DoubleWritable.class);

            FileInputFormat.setInputPaths(job, new Path(arg0[0]));

            String before = TempDayMapper.getInstance().getUserDateBefore().replaceAll("/", ".");
            String after = TempDayMapper.getInstance().getUserDateAfter().replaceAll("/", ".");
           /* if (fs.exists(new Path(arg0[1] + " day " + before + " - " + after)))
                fs.delete(new Path(arg0[1] + " day " + before + " - " + after), true);*/

            FileOutputFormat.setOutputPath(job, new Path(arg0[1] + "_temp_day_" + before + "_" + after));

            return job.waitForCompletion(true) ? 0 : 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new TempDayDriver(), args);
    }
}
