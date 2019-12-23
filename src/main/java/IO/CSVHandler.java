package IO;

import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVHandler {
    private static String writeString = "";
    CSVLoader loader;
//    static String PATH = "D:\\PDPS_res\\";
    static String PATH = "/home/farbeh/PPD/";

    public CSVHandler() {
        loader = new CSVLoader();
    }

    public Instances read(String fileName) {
        try {
            loader.setFile(new File(PATH + fileName));
            return loader.getDataSet();
        } catch (IOException e) {
            if (e.getMessage().equals("File not found")) {
                if (fileName.equals("time_test.csv")) {
                    createTimeTestCSV();
                    try {
                        loader.setFile(new File(PATH + fileName));
                        return loader.getDataSet();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                } else if (fileName.equals("day_test.csv")) {
                    createDayTestCSV();
                    try {
                        loader.setFile(new File(PATH + fileName));
                        return loader.getDataSet();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else
                e.printStackTrace();
        }
        return null;
    }

    static public void time_write(String fileName, String dataIn) {
        writeString += dataIn;
        if (writeString.split(",").length == 57) {
            write(fileName, writeString + "\n", true);
            switch (writeString.split(",")[0].trim()) {
                case "Saturday":
                    CSVHandler.write("day_train.csv", "Saturday,0,TRUE,FASLE", true);
                    break;
                case "Sunday":
                    CSVHandler.write("day_train.csv", "Sunday,0,FASLE,FASLE", true);
                    break;
                case "Monday":
                    CSVHandler.write("day_train.csv", "Monday,0,TRUE,FASLE", true);
                    break;
                case "Tuesday":
                    CSVHandler.write("day_train.csv", "Tuesday,0,FASLE,FASLE", true);
                    break;
                case "Wednesday":
                    CSVHandler.write("day_train.csv", "Wednesday,0,FASLE,TRUE", true);
                    break;
            }
            String presence = "FALSE";
            for (int i = 9; i < 57; i++) {
                if (Boolean.valueOf(writeString.split(",")[i])) {
                    presence = "TRUE";
                    break;
                }
            }
            CSVHandler.write("day_train.csv", "," + presence + "\n", true);
            writeString = "";
            ReportHandler.write("Day Added!");
        }
    }

    private static void write(String fileName, String dataIn, boolean append) {
        try {
            FileWriter fileWriter = new FileWriter(PATH + fileName, append);
            fileWriter.append(dataIn);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (e.getMessage().equals(PATH + fileName + " (The system cannot find the path specified)"))
                new File(PATH).mkdirs();
        }
    }

    synchronized public static void createTimeTestCSV() {
        write("day_test.csv", "Day      ,Tatilness,Class day,Meeting day,Presence\n" +
                "Saturday ,0        ,TRUE     ,FASLE      ,?\n" +
                "Sunday   ,0        ,FASLE    ,FASLE      ,?\n" +
                "Monday   ,0        ,TRUE     ,FASLE      ,?\n" +
                "Tuesday  ,0        ,FASLE    ,FASLE      ,?\n" +
                "Wednesday,0        ,FASLE    ,TRUE       ,?\n", false);
    }

    synchronized public static void createDayTestCSV() {
        write("time_test.csv", "Day      ,Tatilness,Class day,Meeting_Day,class_time_1,class_time_2,class_time_3,class_time_4,class_time_5,7,7:15,7:30,7:45,8,8:15,8:30,8:45,9,9:15,9:30,9:45,10,10:15,10:30,10:45,11,11:15,11:30,11:45,12,12:15,12:30,12:45,13,13:15,13:30,13:45,14,14:15,14:30,14:45,15,15:15,15:30,15:45,16,16:15,16:30,16:45,17,17:15,17:30,17:45,18,18:15,18:30,18:45\n" +
                "Saturday ,0,TRUE     ,FALSE      ,FALSE       ,TRUE        ,FALSE       ,TRUE        ,TRUE        ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                "Sunday   ,0,FALSE    ,FALSE      ,FALSE       ,FALSE       ,FALSE       ,FALSE       ,FALSE       ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                "Monday   ,0,TRUE     ,FALSE      ,FALSE       ,TRUE        ,FALSE       ,TRUE        ,TRUE        ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                "Tuesday  ,0,FALSE    ,FALSE      ,FALSE       ,FALSE       ,FALSE       ,FALSE       ,FALSE       ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                "Wednesday,0,TRUE     ,TRUE       ,FALSE       ,FALSE       ,FALSE       ,FALSE       ,FALSE       ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n", false);

    }
}
