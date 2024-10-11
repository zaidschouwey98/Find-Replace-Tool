package ch.heigvd.dai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(
        name = "hello",
        description = "Print a 'Hello World!' type of message.",
        version = "1.0.0",
        mixinStandardHelpOptions = true)
class Main implements Callable<Integer> {

    @CommandLine.Parameters(
            index = "0",
            description = "The name of the user (default: World).",
            defaultValue = "World")
    protected String name;

    @Override
    public Integer call() {
        System.out.println("Hello " + name + "!");
        return 0;
    }

    public static void main(String... args) {
        BufferedReader br_f, bw_f;
        try(BufferedReader br = new BufferedReader(new FileReader("./test.txt",
                                                    StandardCharsets.UTF_8));
            BufferedWriter bw = new BufferedWriter(new FileWriter("./output_filename.txt",
                                                    StandardCharsets.UTF_8))
            ){
            FileHandler.processText(br, bw, Main::charToUpperCase);
        }catch(Exception e){
            System.out.println("/!\\ Erreur --> " + e.getMessage());
        }
    }
    public static int charToUpperCase(int c){
        if(c <= 'z' && c >= 'a') {
            return Character.toUpperCase(c);
        } else {
            return c;
        }
    }

}
