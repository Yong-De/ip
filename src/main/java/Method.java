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
            System.out.printf("\t%d.%s\n", i + 1, list[i]);
        }
        Line.printLine();
    }

    private static void addToList(String input) {
        list[listSize++] = new Task(input);
        System.out.printf("\tadded: %s\n", input);
        Line.printLine();
    }

    private static void markDone(int index) {
        list[index - 1].setDone(true);
        System.out.println("\tOk, this is done.");
        System.out.printf("\t  %s\n", list[index - 1]);
        Line.printLine();
    }

    private static void markUndone(int index) {
        list[index - 1].setDone(false);
        System.out.println("\tOh, wait i guess this isn't done yet.");
        System.out.printf("\t  %s\n", list[index - 1]);
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
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\tYou need to provide a task number!");
        } catch (NumberFormatException e) {
            System.out.println("\tThat's not a valid number!");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.printf("\tTask doesn't exist! You only have %d tasks!\n", listSize);
        }
    }
}
