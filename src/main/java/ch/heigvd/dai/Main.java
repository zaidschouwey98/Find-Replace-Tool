package ch.heigvd.dai;

import java.util.Objects;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(
        name = "Find-Replace-Tool",
        description = "A small program to perform different action on an input text.",
        version = "1.0.0",
        mixinStandardHelpOptions = true
)

class Main implements Callable<Integer> {
    @CommandLine.Option(
            names = {"-f", "--file"},
            description = "The path of the file to read. Not usable if the parameter --text (-t) is used."
    ) private String input_filePath = "";

    @CommandLine.Option(
            names = {"-t", "--text"},
            description = "The input text to read. Not usable if the parameter --file (-f) is used."
    ) private String input_terminalText = "";

    @CommandLine.Option(
            names = {"-o", "--output"},
            description = "The path of the output file to write in.",
            defaultValue = "output.txt"
    ) private String output_filePath = "";
    @CommandLine.Option(
            names = {"-m", "--mode"},
            description = """
                    The action to perform:
                    -uppercase : Transform all characters in uppercase.
                    -lowercase : Transform all characters in lowercase.
                    -reverse   : Reverse the input text.
                    -find      : Return the number of times a word is found in the text (not case sensitive).
                    -countChar : Return the number of characters in the text.
                    -countWords: Return the number of words in the text.
                    -replace   : Replace a word by an other one (case sensitive).
                    -split     : Split the text into multiple files.
                    """,
            defaultValue = "uppercase"
    ) private String mode;

    @CommandLine.Option(
            names = {"-w", "--word"},
            description = "For the modes 'find' and 'replace': the word to find."
    ) private String word = "";

    @CommandLine.Option(
            names = {"-r", "--replacement"},
            description = "For the mode 'replace': the word to replace the other one (case sensitive)"
    ) private String replacement = "";

    @CommandLine.Option(
            names = {"-lts", "--linestosplit"},
            description = "For the mode 'split': split the text every X lines",
            defaultValue = "2"
    ) private String linesToSplit = "";


    public Integer call() {
        String content;

        // Read file or terminal input, but not both
        if (!input_filePath.isEmpty() && input_terminalText.isEmpty()) { // Read from file
            content = FileHandler.readFile(input_filePath);
            if(Objects.equals(output_filePath, "output.txt"))
                output_filePath = "output_" + input_filePath; // Default name for output file
        } else if (!input_terminalText.isEmpty() && input_filePath.isEmpty()) { // Read from terminal
            content = input_terminalText;
        } else if (!input_terminalText.isEmpty() && !input_filePath.isEmpty()) { // input file and terminal both used
            System.out.println("Choose only one input : file (-f) or text (-t) !");
            return 1;
        } else { // none is used
            System.out.println("Choose one input : file (-f) or text (-t) !");
            return 1;
        }

        if(content!=null) {
            switch(mode){
                case "find":
                    if(!word.isEmpty()) {
                        System.out.println("The word \"" + word + "\" appears " +
                                TextHandler.countOccurrences(content, word) + " times in the text !");
                        break;
                    } else {
                        System.out.println("/!\\ This mode requires the parameters 'word' (-w) to be used !");
                        return 1;
                    }
                case "countWords":
                    System.out.println("There's " + TextHandler.countWords(content) + " words in the text !");
                    break;
                case "countChar":
                    System.out.println("There's " + TextHandler.countChar(content) + " characters in the text !");
                    break;
                case "split":
                    String[] texts = TextHandler.splitText(content, Integer.parseInt(linesToSplit));
                    for(int i = 0; i < texts.length; i++) {
                        FileHandler.writeFile("split_" +(i+1) +"_"+ output_filePath, texts[i]);
                    }
                    break;
                case "replace":
                    if (!word.isEmpty() && !replacement.isEmpty()) {
                        content = TextHandler.replaceWord(content, word, replacement);
                        FileHandler.writeFile(output_filePath, content);
                        break;
                    } else {
                        System.out.println("/!\\ This mode requires the parameters 'word' (-w) and 'replacement' (-r)" +
                                " to be used !");
                        return 1;
                    }
                default:
                    content = TextHandler.transform(content, mode);
                    FileHandler.writeFile(output_filePath, content);
                    break;
            }

            System.out.println("Job's done !");
            return 0;
        } else {
            System.out.println("Error during execution !");
            return 1;
        }
    }

    public static void main(String[] args) {
        Long start = System.nanoTime();

        // create instance of main and execute Picocli
        int exitCode = new CommandLine(new Main()).execute(args);

        Long end = System.nanoTime();

        if (exitCode == 0) {
            System.out.println("Execution time in ms: " + (end - start) / (1000 * 1000));
        }

        System.exit(exitCode);
    }
}