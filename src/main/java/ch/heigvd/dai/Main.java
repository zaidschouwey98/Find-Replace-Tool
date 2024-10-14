package ch.heigvd.dai;
import java.util.Objects;
import java.util.concurrent.Callable;

import picocli.CommandLine;

@CommandLine.Command(
        name = "Find-Replace-Tool",
        description = "Executes different action on an input text.",
        version = "1.0.0",
        mixinStandardHelpOptions = true)

class Main implements Callable<Integer> {

    @CommandLine.Option(
            names = {"-f", "--file"},
            description = "The path of the file to read"
    ) private String input_filePath = "";

    @CommandLine.Option(
            names = {"-t", "--text"},
            description = "The input text to read"
    ) private String input_terminalText = "";

    @CommandLine.Option(
            names = {"-o", "--output"},
            description = "The path of the file to write in"
    ) private String output_filePath = "";

    @CommandLine.Option(
            names = {"-w", "--word"},
            description = "The word to find"
    ) private String find_word = "";

    @CommandLine.Option(
            names = {"-m", "--mode"},
            description = "The function to apply : uppercase, lowercase, reverse",
            defaultValue = "uppercase"
    ) private String mode;

    public Integer call() {
        String content;

        // Read file or terminal input
        if (!input_filePath.isEmpty() && input_terminalText.isEmpty()) { // Read from file
            content = FileHandler.readFile(input_filePath);
        } else if (!input_terminalText.isEmpty() && input_filePath.isEmpty()) { // Read from terminal
            content = input_terminalText;
        } else { // input_filePath and input_terminalText are both used or none
            System.out.println("Choose only one input : file (-f) or text (-t) !");
            return 1;
        }

        if(content!=null){
            if (Objects.equals(mode, "find")){
                System.out.println("The word \"" + find_word + "\" appears " +
                        TextHandler.countOccurrences(content, find_word) + " times in the text !");
            } else {
                // Apply function to modify text
                content = TextHandler.transform(content, mode);
                // Write in output file
                FileHandler.writeFile(output_filePath, content);
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