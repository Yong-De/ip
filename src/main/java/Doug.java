public class Doug {
    private static String banner = " ____\n"
            + "|  _ \\  ___  _   _  __ _\n"
            + "| | | |/ _ \\| | | |/ _` |\n"
            + "| |_| | (_) | |_| | (_| |\n"
            + "|____/ \\___/ \\__,_|\\__, |\n"
            + "                   |___/\n";
    private static String name = "Doug";

    public static void main(String[] args) {
        initialization();
        termination();
    }

    private static void initialization() {
        printLine();
        System.out.println(banner);
        System.out.println(String.format(
                "Hello, this is %s%s Where we solve problems no one has.", name, name));
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void termination() {
        System.out.println("Peace.");
        printLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
