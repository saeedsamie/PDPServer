package IO;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;

public class ReportHandler {
    static String fileName = "report.txt";

    public static void write(String dataIn) {

        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fileWriter.append(String.valueOf(timestamp)).append(" : ").append(dataIn).append("<br>");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFileAsString(String fileName) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException ignored) {
        }
        return data;
    }

    public static String read() {
        String data = "";
        try {
            data = readFileAsString(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
