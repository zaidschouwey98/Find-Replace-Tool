package ch.heigvd.dai;

import java.security.KeyPair;
import java.util.Arrays;
import java.util.function.Function;

public class TextHandler {
    public static Function<String, String> getFunction(String mode) {
        return switch (mode) {
            case "lowercase" -> String::toLowerCase;
            case "reverse" -> s -> new StringBuilder(s).reverse().toString();
            default -> String::toUpperCase;
        };
    }

    public static String transform(String content, String mode) {
        Function<String, String> function = getFunction(mode);
        return function.apply(content);
    }

    public static int countOccurrences(String text, String word) {
        int count = 0;

        // Transform everything to lowercase char for an easier comparison
        String lowerCaseText = transform(text, "lowercase");
        String lowerCaseWord = transform(word, "lowercase");

        // The text is split every blank space (\b)
        String[] splitText = lowerCaseText.split("[\\s\\p{Punct}]+");

        for (String w : splitText) {
            if (w.equals(lowerCaseWord)) {
                count++;
            }
        }

        return count;
    }

    public static int countWords(String text) {
        int count = 0;

        String[] splitText = text.split("[\\s\\p{Punct}]+");

        for (String w : splitText) {
            if (w.matches("[a-zA-Zà-ÿÀ-Ÿ]+([-'][a-zA-Zà-ÿÀ-Ÿ]+)*")) {
                count++;
            }
        }

        return count;
    }

    public static String[] splitText(String text, int nbToSplit){
        int arraySize = 8;
        int splittedTextIndex = 0;

        String[] splittedText = new String[arraySize];
        String[] lines = text.split("\n");
        String currentLine = "";


        for(int i = 0; i < lines.length; i++){
            currentLine += lines[i];
            if((i + 1) % nbToSplit == 0){
                if(splittedTextIndex == arraySize-1){
                    // Double the size
                    String[] tempArray = Arrays.copyOf(splittedText, arraySize*=2);
                    splittedText = tempArray;
                }
                splittedText[splittedTextIndex++] = currentLine;
                currentLine = "";
            }
        }
        if(!currentLine.isEmpty())
            splittedText[splittedTextIndex] = currentLine;

        // Resize the array to fit content
        String[] returnedSplittedText  = Arrays.copyOf(splittedText, splittedTextIndex);
        return returnedSplittedText;
    }
}
