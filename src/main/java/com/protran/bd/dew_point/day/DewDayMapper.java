package com.protran.bd.dew_point.day;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DewDayMapper extends Mapper<LongWritable, Text, Text, FloatWritable> {
    private String userDateBefore;
    private String userDateAfter;
    private final static DewDayMapper INSTANCE = new DewDayMapper();

    private DewDayMapper() {}

    public static DewDayMapper getInstance() {
        return INSTANCE;
    }

    public String getUserDateBefore() {
        return userDateBefore;
    }

    public void setUserDateBefore(String userDateBefore) {
        this.userDateBefore = userDateBefore;
    }

    public String getUserDateAfter() {
        return userDateAfter;
    }

    public void setUserDateAfter(String userDateAfter) {
        this.userDateAfter = userDateAfter;
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        try {
            //MM/DD/YYYY
           /* String userDateBefore = "03/01/11";
            String userDateAfter = "05/01/11";*/
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("MM/dd/yy");
            Date userDateBeforeFormatted = null;
            try {
                userDateBeforeFormatted = format.parse(DewDayMapper.getInstance().getUserDateBefore());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date userDateAfterFormatted = null;
            try {
                userDateAfterFormatted = format.parse(DewDayMapper.getInstance().getUserDateAfter());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String[] strArr = value.toString().split(",");
            String date = strArr[2];
            Date incomeDate = null;
            try {
                incomeDate = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat dateFormat;
            Text dateText;
            float airTemp;
            if((incomeDate.before(userDateAfterFormatted) || incomeDate.equals(userDateAfterFormatted))
                    && (incomeDate.after(userDateBeforeFormatted) || incomeDate.equals(userDateBeforeFormatted))) {
                dateFormat = new SimpleDateFormat("MM/dd/yy");
                dateText = new Text(dateFormat.format(incomeDate));
                airTemp = Float.parseFloat(strArr[14]);
                context.write(dateText, new FloatWritable(airTemp));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    public static LocalDateTime setDate(LocalDateTime localDateTime){
        return localDateTime;
    }*/
}