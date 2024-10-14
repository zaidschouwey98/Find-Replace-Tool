package ch.heigvd.dai;

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

        if (text == null || word == null || word.isEmpty()) {
            System.out.println("/!\\ Error : input text or word is null or empty !");
            return -1;
        }

        // Transform everything to lowercase char for an easier comparison
        String lowerCaseText = transform(text, "lowercase");
        String lowerCaseWord = transform(word, "lowercase");

        // The text is split every blank space (\b)
        String[] splitText = lowerCaseText.split("\\b");

        for (String w : splitText) {
            if (w.equals(lowerCaseWord)) {
                count++;
            }
        }

        return count;
    }

}
