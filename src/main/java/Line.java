public class Line {
    // Lines
    private static String name = "Doug";

    private static String banner = "\t ____\n"
            + "\t|  _ \\  ___  _   _  __ _\n"
            + "\t| | | |/ _ \\| | | |/ _` |\n"
            + "\t| |_| | (_) | |_| | (_| |\n"
            + "\t|____/ \\___/ \\__,_|\\__, |\n"
            + "\t                   |___/\n";

    private static String intro = String.format(
            "\tHey, this is %s%s Where we solve problems no one has.\n\n\tWhat can I do for you?", name, name);

    private static String outro = "\tPeace.";

    private static String line = "\t____________________________________________________________";

    // Printers
    public static void printName() {
        System.out.println(name);
    }

    public static void printBanner() {
        System.out.println(banner);
    }

    public static void printIntro() {
        System.out.println(intro);
    }

    public static void printOutro() {
        System.out.println(outro);
    }

    public static void printLine() {
        System.out.println(line);
    }
}
