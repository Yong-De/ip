public class Method {
    private static Task[] list = new Task[100];
    private static int listSize = 0;

    private static void echo(String input) {
        System.out.printf("\t %s\n", input);
        Line.printLine();
    }

    private static void getList() {
        System.out.println("\tHere's what you have to do:");
        for (int i = 0; i < listSize; ++i) {
            System.out.printf("\t%d.%s\n", i + 1, getTask(list[i]));
        }
        Line.printLine();
    }

    public static String getTask(Task task) {
        return String.format("[%c] %s", (task.getIsDone()) ? 'X' : ' ', task.getName());
    }

    private static void addToList(String input) {
        list[listSize++] = new Task(input);
        System.out.printf("\tadded: %s\n", input);
        Line.printLine();
    }

    private static void markDone(int index) {
        list[index - 1].setDone(true);
        System.out.println("\tOk, this is done.");
        System.out.printf("\t  %s\n", getTask(list[index - 1]));
        Line.printLine();
    }

    private static void markUndone(int index) {
        list[index - 1].setDone(false);
        System.out.println("\tOh, wait i guess this isn't done yet.");
        System.out.printf("\t  %s\n", getTask(list[index - 1]));
        Line.printLine();
    }

    public static void reply(String input) {
        String[] words = input.split(" ");
        try {
            switch (words[0].toLowerCase()) {
                case "list" -> getList();
                case "mark" -> markDone(Integer.parseInt(words[1]));
                case "unmark" -> markUndone(Integer.parseInt(words[1]));
                default -> addToList(input);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
