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
            FileWriter fileWriter = new FileWriter("C:\\Users\\Saeed\\Documents\\Graduation Final Project\\PDPServer\\" + fileName, true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fileWriter.append(String.valueOf(timestamp)).append(" : ").append(dataIn).append("<br>");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static String read() {
        String data = "";
        try {
            data = readFileAsString("C:\\Users\\Saeed\\Documents\\Graduation Final Project\\PDPServer\\" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
