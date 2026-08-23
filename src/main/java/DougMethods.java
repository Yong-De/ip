public class DougMethods {
    private static void echo(String input) {
        System.out.printf("\t %s\n", input);
        DougLines.printLine();
    }

    public static void reply(String input) {
        echo(input);
    }
}
