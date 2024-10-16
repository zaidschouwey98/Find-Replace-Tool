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
}
