package ch.heigvd.dai;

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
        int exitCode = new CommandLine(new Main()).execute(args);

        System.exit(exitCode);
    }
}