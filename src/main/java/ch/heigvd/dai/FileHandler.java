package ch.heigvd.dai;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {

    public static String readFile(String filePath, Charset charset) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), charset)) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("/!\\ Error with file reading : " + e.getMessage());
            System.exit(1);
        }
        return content.toString();
    }

    public static void writeFile(String filePath, String content, Charset charset) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filePath), charset)) {
            bw.write(content);
        } catch (IOException e) {
            System.err.println("/!\\ Error with file writing : " + e.getMessage());
            System.exit(1);
        }
    }
}
