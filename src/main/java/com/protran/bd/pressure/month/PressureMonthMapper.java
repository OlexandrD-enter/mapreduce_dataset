package com.protran.bd.pressure.month;

import com.protran.bd.pressure.day.PressureDayMapper;
import com.protran.bd.temperature.day.TempDayMapper;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class PressureMonthMapper extends Mapper<LongWritable, Text, Text, FloatWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        try {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("MM/dd/yy");
            Date userDateBeforeFormatted = null;
            try {
                userDateBeforeFormatted = format.parse(PressureDayMapper.getInstance().getUserDateBefore());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date userDateAfterFormatted = null;
            try {
                userDateAfterFormatted = format.parse(PressureDayMapper.getInstance().getUserDateAfter());
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
            Calendar cal = Calendar.getInstance();
            cal.setTime(userDateBeforeFormatted);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(userDateAfterFormatted);
            long fullMonth = getFullMonth(cal,cal2);
            cal.add(Calendar.MONTH, (int) fullMonth);
            Date untilDate = cal.getTime();
           /* System.out.println(userDateBeforeFormatted);
            System.out.println(fullMonth);
            System.out.println(untilDate);*/
            SimpleDateFormat dateFormat;
            Text dateText;
            float pressure;
            if((incomeDate.before(untilDate) || incomeDate.equals(untilDate))
                    && (incomeDate.after(userDateBeforeFormatted) || incomeDate.equals(userDateBeforeFormatted))) {
                dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                dateText = new Text(dateFormat.format(incomeDate));
                pressure = Float.parseFloat(strArr[7]);
                context.write(dateText, new FloatWritable(pressure));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static long getFullMonth(Calendar d1, Calendar d2){

        Instant d1i = Instant.ofEpochMilli(d1.getTimeInMillis());
        Instant d2i = Instant.ofEpochMilli(d2.getTimeInMillis());

        LocalDateTime startDate = LocalDateTime.ofInstant(d1i, ZoneId.systemDefault());
        LocalDateTime endDate = LocalDateTime.ofInstant(d2i, ZoneId.systemDefault());

        return ChronoUnit.MONTHS.between(startDate, endDate);
    }
}
