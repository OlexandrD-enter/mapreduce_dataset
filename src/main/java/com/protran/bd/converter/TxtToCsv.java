package com.protran.bd.converter;

import com.protran.bd.dew_point.day.DewDayMapper;
import com.protran.bd.pressure.day.PressureDayMapper;
import com.protran.bd.temperature.day.TempDayMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TxtToCsv {
    public static void converterTemp(String period) throws IOException {
        if (period.equals("day")) {
            FileWriter writer;
            String name = TempDayMapper.getInstance().getUserDateBefore() + "_" + TempDayMapper.getInstance().getUserDateAfter();
            name = name.replaceAll("/", ".");
            File file = new File("C:\\Users\\olexa\\Desktop\\output\\" + "temp_" + period + "_" + name + ".txt");
            Scanner scan = new Scanner(file);
            File file2 = new File("C:\\Users\\olexa\\Desktop\\output\\" + "temp_" + period + "_" + name + ".csv");
            file.createNewFile();
            writer = new FileWriter(file2);
            String csv = "";
            csv = scan.nextLine();
            writer.append(csv.substring(0, 3)).append(";").append(csv.substring(13, 21)).append("\n");
            Scanner scan2 = new Scanner(file);
            while (scan2.hasNext()) {
                csv = scan2.nextLine().replace(".", ",").replace("/", ".")
                        .replace("Day ", "")
                        .replace(" avg_temp ", ";");
                writer.append(csv);
                writer.append("\n");
                writer.flush();
            }
        }
        if (period.equals("week")) {
            FileWriter writer;
            String name = TempDayMapper.getInstance().getUserDateBefore() + "_" + TempDayMapper.getInstance().getUserDateAfter();
            name = name.replaceAll("/", ".");
            File file = new File("C:\\Users\\olexa\\Desktop\\output\\" + "temp_" + period + "_" + name + ".txt");
            Scanner scan = new Scanner(file);
            File file2 = new File("C:\\Users\\olexa\\Desktop\\output\\" + "temp_" + period + "_" + name + ".csv");
            file.createNewFile();
            writer = new FileWriter(file2);
            String csv = "";
            csv = scan.nextLine();
            writer.append(csv.substring(0, 4)).append(";").append(csv.substring(27, 35)).append("\n");
            Scanner scan2 = new Scanner(file);
            while (scan2.hasNext()) {
                csv = scan2.nextLine().replace(".", ",").replace("/", ".")
                        .replace("(", "")
                        .replace(")", "")
                        .replace("Week: ", "")
                        .replace(" avg_temp: ", "");
                writer.append(csv);
                writer.append("\n");
                writer.flush();
            }
        }
        if (period.equals("month")) {
            FileWriter writer = null;
            String name = TempDayMapper.getInstance().getUserDateBefore() + "_" + TempDayMapper.getInstance().getUserDateAfter();
            name = name.replaceAll("/", ".");
            File file = new File("C:\\Users\\olexa\\Desktop\\output\\" + "temp_" + period + "_" + name + ".txt");
            Scanner scan = new Scanner(file);
            File file2 = new File("C:\\Users\\olexa\\Desktop\\output\\" + "temp_" + period + "_" + name + ".csv");
            file.createNewFile();
            writer = new FileWriter(file2);
            String csv = "";
            csv = scan.nextLine();
            writer.append(csv.substring(0, 5)).append(";").append(csv.substring(15, 23)).append("\n");
            Scanner scan2 = new Scanner(file);
            while (scan2.hasNext()) {
                csv = scan2.nextLine().replace(".", ",").replace("/", ".")
                        .replace("Month: ", "")
                        .replace(" avg_temp: ", ";");
                writer.append(csv);
                writer.append("\n");
                writer.flush();
            }
        }
    }


    public static void converterPress(String period) throws IOException {
        if (period.equals("day")) {
            FileWriter writer;
            String name = PressureDayMapper.getInstance().getUserDateBefore() + "_" + PressureDayMapper.getInstance().getUserDateAfter();
            name = name.replaceAll("/", ".");
            File file = new File("C:\\Users\\olexa\\Desktop\\output\\" + "pressure_" + period + "_" + name + ".txt");
            Scanner scan = new Scanner(file);
            File file2 = new File("C:\\Users\\olexa\\Desktop\\output\\" + "pressure_" + period + "_" + name + ".csv");
            file.createNewFile();
            writer = new FileWriter(file2);
            String csv = "";
            csv = scan.nextLine();
            writer.append(csv.substring(0, 3)).append(";").append(csv.substring(13, 25)).append("\n");
            Scanner scan2 = new Scanner(file);
            while (scan2.hasNext()) {
                csv = scan2.nextLine().replace(".", ",").replace("/", ".")
                        .replace("Day ", "")
                        .replace(" avg_pressure ", ";");
                writer.append(csv);
                writer.append("\n");
                writer.flush();
            }
        }
        if (period.equals("week")) {
            FileWriter writer;
            String name = PressureDayMapper.getInstance().getUserDateBefore() + "_" + PressureDayMapper.getInstance().getUserDateAfter();
            name = name.replaceAll("/", ".");
            File file = new File("C:\\Users\\olexa\\Desktop\\output\\" + "pressure_" + period + "_" + name + ".txt");
            Scanner scan = new Scanner(file);
            File file2 = new File("C:\\Users\\olexa\\Desktop\\output\\" + "pressure_" + period + "_" + name + ".csv");
            file.createNewFile();
            writer = new FileWriter(file2);
            String csv = "";
            csv = scan.nextLine();
            writer.append(csv.substring(0, 4)).append(";").append(csv.substring(27, 39)).append("\n");
            Scanner scan2 = new Scanner(file);
            while (scan2.hasNext()) {
                csv = scan2.nextLine().replace(".", ",").replace("/", ".")
                        .replace("(", "")
                        .replace(")", "")
                        .replace("Week: ", "")
                        .replace(" avg_pressure: ", "");
                writer.append(csv);
                writer.append("\n");
                writer.flush();
            }
        }
        if (period.equals("month")) {
            FileWriter writer = null;
            String name = PressureDayMapper.getInstance().getUserDateBefore() + "_" + PressureDayMapper.getInstance().getUserDateAfter();
            name = name.replaceAll("/", ".");
            File file = new File("C:\\Users\\olexa\\Desktop\\output\\" + "pressure_" + period + "_" + name + ".txt");
            Scanner scan = new Scanner(file);
            File file2 = new File("C:\\Users\\olexa\\Desktop\\output\\" + "pressure_" + period + "_" + name + ".csv");
            file.createNewFile();
            writer = new FileWriter(file2);
            String csv = "";
            csv = scan.nextLine();
            writer.append(csv.substring(0, 5)).append(";").append(csv.substring(15, 27)).append("\n");
            Scanner scan2 = new Scanner(file);
            while (scan2.hasNext()) {
                csv = scan2.nextLine().replace(".", ",").replace("/", ".")
                        .replace("Month: ", "")
                        .replace(" avg_pressure: ", ";");
                writer.append(csv);
                writer.append("\n");
                writer.flush();
            }
        }
    }

    public static void converterDew(String period) throws IOException {
        if (period.equals("day")) {
            FileWriter writer;
            String name = DewDayMapper.getInstance().getUserDateBefore() + "_" + DewDayMapper.getInstance().getUserDateAfter();
            name = name.replaceAll("/", ".");
            File file = new File("C:\\Users\\olexa\\Desktop\\output\\" + "dew_" + period + "_" + name + ".txt");
            Scanner scan = new Scanner(file);
            File file2 = new File("C:\\Users\\olexa\\Desktop\\output\\" + "dew_" + period + "_" + name + ".csv");
            file.createNewFile();
            writer = new FileWriter(file2);
            String csv = "";
            csv = scan.nextLine();
            writer.append(csv.substring(0, 3)).append(";").append(csv.substring(13, 20)).append("\n");
            Scanner scan2 = new Scanner(file);
            while (scan2.hasNext()) {
                csv = scan2.nextLine().replace(".", ",").replace("/", ".")
                        .replace("Day ", "")
                        .replace(" avg_dew ", ";");
                writer.append(csv);
                writer.append("\n");
                writer.flush();
            }
        }
        if (period.equals("week")) {
            FileWriter writer;
            String name = DewDayMapper.getInstance().getUserDateBefore() + "_" + DewDayMapper.getInstance().getUserDateAfter();
            name = name.replaceAll("/", ".");
            File file = new File("C:\\Users\\olexa\\Desktop\\output\\" + "dew_" + period + "_" + name + ".txt");
            Scanner scan = new Scanner(file);
            File file2 = new File("C:\\Users\\olexa\\Desktop\\output\\" + "dew_" + period + "_" + name + ".csv");
            file.createNewFile();
            writer = new FileWriter(file2);
            String csv = "";
            csv = scan.nextLine();
            writer.append(csv.substring(0, 4)).append(";").append(csv.substring(27, 34)).append("\n");
            Scanner scan2 = new Scanner(file);
            while (scan2.hasNext()) {
                csv = scan2.nextLine().replace(".", ",").replace("/", ".")
                        .replace("(", "")
                        .replace(")", "")
                        .replace("Week: ", "")
                        .replace(" avg_dew: ", "");
                writer.append(csv);
                writer.append("\n");
                writer.flush();
            }
        }
        if (period.equals("month")) {
            FileWriter writer = null;
            String name = DewDayMapper.getInstance().getUserDateBefore() + "_" + DewDayMapper.getInstance().getUserDateAfter();
            name = name.replaceAll("/", ".");
            File file = new File("C:\\Users\\olexa\\Desktop\\output\\" + "dew_" + period + "_" + name + ".txt");
            Scanner scan = new Scanner(file);
            File file2 = new File("C:\\Users\\olexa\\Desktop\\output\\" + "dew_" + period + "_" + name + ".csv");
            file.createNewFile();
            writer = new FileWriter(file2);
            String csv = "";
            csv = scan.nextLine();
            writer.append(csv.substring(0, 5)).append(";").append(csv.substring(15, 22)).append("\n");
            Scanner scan2 = new Scanner(file);
            while (scan2.hasNext()) {
                csv = scan2.nextLine().replace(".", ",").replace("/", ".")
                        .replace("Month: ", "")
                        .replace(" avg_dew: ", ";");
                writer.append(csv);
                writer.append("\n");
                writer.flush();
            }
        }
    }
}
