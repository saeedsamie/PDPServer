package IO;

import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVHandler {
    private static String writeString = "";

    static public Instances read(String fileName) {
        try {
            CSVLoader loader = new CSVLoader();
            loader.setSource(new File("C:\\Users\\Saeed\\Documents\\Graduation Final Project\\PDPServer\\" + fileName));
            return loader.getDataSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public void time_write(String fileName, String dataIn) {
        writeString += dataIn;
        if (writeString.split(",").length == 57) {
            write(fileName, writeString);
            switch (writeString.split(",")[0].trim()) {
                case "Saturday":
                    CSVHandler.write("day_train.csv", "\nSaturday,0,TRUE,FASLE");
                    break;
                case "Sunday":
                    CSVHandler.write("day_train.csv", "\nSunday,0,FASLE,FASLE");
                    break;
                case "Monday":
                    CSVHandler.write("day_train.csv", "\nMonday,0,TRUE,FASLE");
                    break;
                case "Tuesday":
                    CSVHandler.write("day_train.csv", "\nTuesday,0,FASLE,FASLE");
                    break;
                case "Wednesday":
                    CSVHandler.write("day_train.csv", "\nWednesday,0,FASLE,TRUE");
                    break;
            }
            String presence = "FALSE";
            for (int i = 9; i < 57; i++) {
                if (Boolean.valueOf(writeString.split(",")[i])) {
                    presence = "TRUE";
                    break;
                }
            }
            CSVHandler.write("day_train.csv", "," + presence);
            writeString = "";
        }
    }

    static public void write(String fileName, String dataIn) {
        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\Saeed\\Documents\\Graduation Final Project\\PDPServer\\" + fileName, true);
            fileWriter.append(dataIn);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
