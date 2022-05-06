package com.protran.bd.converter;
import com.protran.bd.dew_point.day.DewDayMapper;
import com.protran.bd.pressure.day.PressureDayMapper;
import com.protran.bd.temperature.day.TempDayMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class CopyToLocal {
    public static void copyToLocalTemp(String period) throws IOException {
        String name = TempDayMapper.getInstance().getUserDateBefore() + "_" + TempDayMapper.getInstance().getUserDateAfter();
        name = name.replaceAll("/", ".");
        String dest = "hdfs://localhost:9000/hadoop/output_temp_" + period + "_" + name + "/part-r-00000";
        String local = "C:\\Users\\olexa\\Desktop\\output\\" + "temp_" + period + "_" + name + ".txt";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dest),conf);
        FSDataInputStream fsdi = fs.open(new Path(dest));
        OutputStream output = new FileOutputStream(local);
        IOUtils.copyBytes(fsdi,output,4096,true);
    }
    public static void copyToLocalPress(String period) throws IOException {
        String name = PressureDayMapper.getInstance().getUserDateBefore() + "_" + PressureDayMapper.getInstance().getUserDateAfter();
        name = name.replaceAll("/", ".");
        String dest = "hdfs://localhost:9000/hadoop/output_pressure_" + period + "_" + name + "/part-r-00000";
        String local = "C:\\Users\\olexa\\Desktop\\output\\" + "pressure_" + period + "_" + name + ".txt";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dest),conf);
        FSDataInputStream fsdi = fs.open(new Path(dest));
        OutputStream output = new FileOutputStream(local);
        IOUtils.copyBytes(fsdi,output,4096,true);
    }
    public static void copyToLocalDew(String period) throws IOException {
        String name = DewDayMapper.getInstance().getUserDateBefore() + "_" + DewDayMapper.getInstance().getUserDateAfter();
        name = name.replaceAll("/", ".");
        String dest = "hdfs://localhost:9000/hadoop/output_dew_" + period + "_" + name + "/part-r-00000";
        String local = "C:\\Users\\olexa\\Desktop\\output\\" + "dew_" + period + "_" + name + ".txt";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dest),conf);
        FSDataInputStream fsdi = fs.open(new Path(dest));
        OutputStream output = new FileOutputStream(local);
        IOUtils.copyBytes(fsdi,output,4096,true);
    }
}
