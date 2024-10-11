package ch.heigvd.dai;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

public class FileHandler {
    public static void processText (BufferedReader br, BufferedWriter bw, Function < Integer, Integer > fn) throws IOException {
        int c;
        while ((c = br.read()) != -1) {
            int modifiedChar = fn.apply(c);
            bw.write(modifiedChar);
        }
    }

}
