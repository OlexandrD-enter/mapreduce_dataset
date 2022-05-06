package com.protran.bd.pressure.day;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class PressureDayDriver extends Configured implements Tool {
    public int run(String[] arg0) {
        try {
            FileSystem fs = FileSystem.get(getConf());

            Job job = Job.getInstance(getConf());
            job.setJarByClass(getClass());

            job.setMapperClass(PressureDayMapper.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(FloatWritable.class);

            job.setReducerClass(PressureDayReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(DoubleWritable.class);

            FileInputFormat.setInputPaths(job, new Path(arg0[0]));

            String before = PressureDayMapper.getInstance().getUserDateBefore().replaceAll("/", ".");
            String after = PressureDayMapper.getInstance().getUserDateAfter().replaceAll("/", ".");
           /* if (fs.exists(new Path(arg0[1] + " day " + before + " - " + after)))
                fs.delete(new Path(arg0[1] + " day " + before + " - " + after), true);*/

            FileOutputFormat.setOutputPath(job, new Path(arg0[1] + "_pressure_day_" + before + "_" + after));

            return job.waitForCompletion(true) ? 0 : 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new PressureDayDriver(), args);
    }
}
