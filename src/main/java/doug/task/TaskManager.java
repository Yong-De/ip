package doug.task;

import doug.errors.DougException;
import doug.ui.Line;

public class TaskManager {
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

    private static void addToList(String input, Task.Type taskType) {
        String[] words = input.split(" ", 2);
        try {
            String description = words[1];
            Task task = new Task(description, taskType);
            list[listSize++] = task;
            System.out.printf("\tAlright your %s is in the list:\n\t  %s\n", taskType.toString(), task);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.printf("\tWhat %s are you trying to add?\n\tYou need to give a description here.\n",
                    taskType.toString());
        }
        Line.printLine();
    }

    private static void markDone(boolean isDone, String input) {
        String[] words = input.split(" ", 2);
        try {
            int index = Integer.parseInt(words[1]);
            list[index - 1].setDone(isDone);
            if (isDone)
                System.out.println("\tOk, this is done.");
            else
                System.out.println("\tOh, wait i guess this isn't done yet.");
            System.out.printf("\t  %s\n", list[index - 1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\tWhat are you trying to mark here?\n\tI need the index of the task.");
        } catch (NumberFormatException e) {
            System.out.printf("\t%s Is not a number.\n\tPut a number please.\n", words[1]);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("\tWhat? That doesn't exist yet.\n\tMark something thats already in!");
        }
        Line.printLine();
    }

    public static void reply(String input) {
        String[] words = input.split(" ", 2);
        switch (words[0].toLowerCase()) {
            case "list" -> getList();
            case "mark" -> markDone(true, input);
            case "unmark" -> markDone(false, input);
            case "todo" -> addToList(input, Task.Type.TODO);
            case "deadline" -> addToList(input, Task.Type.DEADLINE);
            case "event" -> addToList(input, Task.Type.EVENT);
            default -> throw new DougException();
        }
    }
}
