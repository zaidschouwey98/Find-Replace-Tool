package ch.heigvd.dai;

import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextHandler {
    //Only accessible to this class
    Function<String, String> getFunction(String mode) {
        return switch (mode) {
            case "lowercase" -> String::toLowerCase;
            case "reverse" -> s -> new StringBuilder(s).reverse().toString();
            default -> String::toUpperCase;
        };
    }

    public static String transform(String content, String mode) {
        TextHandler textHandler = new TextHandler();
        Function<String, String> function = textHandler.getFunction(mode);
        return function.apply(content);
    }

    public static int countOccurrences(String text, String word) {
        int count = 0;

        // all to lowercase for better comparison
        String lowerCaseText = transform(text, "lowercase");
        String lowerCaseWord = transform(word, "lowercase");

        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(lowerCaseWord) + "\\b");
        Matcher matcher = pattern.matcher(lowerCaseText);

        while (matcher.find()) {
            count++;
        }

        return count;
    }

    public static String replaceWord(String text, String word, String replacement) {
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b");
        Matcher matcher = pattern.matcher(text);

        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(result, replacement); // replace the words
        }
        matcher.appendTail(result);  // Add the remaining of the text

        return result.toString();
    }

    public static int countWords(String text){
        int count = 0;

        String[] splitText = text.split("[\\s\\p{Punct}]+");

        for (String w : splitText) {
            if (w.matches("[a-zA-Zà-ÿÀ-Ÿ]+([-'][a-zA-Zà-ÿÀ-Ÿ]+)*")) {
                count++;
            }
        }
        return count;
    }

    public static int countChar(String text){
        int count = 0;

        for (char c : text.toCharArray()) {
           if (c != ' ' && c != '\n' && c != '\r') {
               count++;
           }
        }
        return count;
    }

    public static String[] splitText(String text, int nbToSplit){
        if(nbToSplit <= 0){
            System.err.println("The number used can't be <= 0 !");
            System.exit(1);
        }
        int arraySize = 8;
        int splittedTextIndex = 0;

        String[] splittedText = new String[arraySize];
        String[] lines = text.split("\n");
        String currentLine = "";

        for(int i = 0; i < lines.length; i++){
            currentLine += lines[i] +'\n';
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

        if(!currentLine.isEmpty()){
            splittedText[splittedTextIndex] = currentLine;
            splittedTextIndex++;
        }

        // Resize the array to fit content
        String[] returnedSplittedText  = Arrays.copyOf(splittedText, splittedTextIndex);
        return returnedSplittedText;
    }
}
