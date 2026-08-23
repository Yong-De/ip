public class DougMethods {
    private static String[] list = new String[100];
    private static int listSize = 0;

    private static void echo(String input) {
        System.out.printf("\t %s\n", input);
        DougLines.printLine();
    }

    private static void getList() {
        for (int i = 0; i < listSize; ++i) {
            System.out.printf("\t%d. %s\n", i + 1, list[i]);
        }
        DougLines.printLine();
    }

    private static void addToList(String input) {
        list[listSize++] = input;
        System.out.printf("\tadded: %s\n", input);
        DougLines.printLine();
    }

    public static void reply(String input) {
        switch (input.toLowerCase()) {
            case "list" -> getList();
            default -> addToList(input);
        }
    }
}
