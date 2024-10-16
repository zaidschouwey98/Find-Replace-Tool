package ch.heigvd.dai;

import java.io.*;

public class FileHandler {

    public static String readFile(String filePath) {
        String content = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content += (line + "\n");
            }
        } catch (IOException e) {
            System.out.println("/!\\ Error with file reading : " + e.getMessage());
            System.exit(1);
        }
        return content;
    }

    public static void writeFile(String filePath, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
        } catch (IOException e) {
            System.out.println("/!\\ Error with file writing : " + e.getMessage());
            System.exit(1);
        }
    }
}
