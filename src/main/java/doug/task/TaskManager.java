package doug.task;

import java.util.ArrayList;
import java.util.EnumSet;

import doug.errors.DougException;
import doug.ui.Line;

public class TaskManager {
    private static ArrayList<Task> list = new ArrayList<>();

    private static void echo(String input) {
        System.out.printf("\t %s\n", input);
        Line.printLine();
    }

    private static void printTaskSyntax(Task.Type taskType) {
        System.out.printf("%s <description>\n", taskType.toString().toLowerCase());
    }

    private static void printHelp() {
        System.out.println("Here are the list of commands");

        System.out.println("\n---- List Tasks ----");
        System.out.println("list");

        System.out.println("\n---- Adding Tasks ----");
        EnumSet.allOf(Task.Type.class).forEach(Type -> printTaskSyntax(Type));

        System.out.println("\n---- Marking Tasks ----");
        System.out.println("mark <index>");
        System.out.println("unmark <index>");

        System.out.println("\n---- Deleting Tasks ----");
        System.out.println("delete <index>");

        System.out.println("\n---- Exiting ----");
        System.out.println("bye");

        Line.printLine();
    }

    private static void printList() {
        if (list.size() <= 0)
            System.out.println("\tYou haven't added anything yet. Start adding.");
        else {
            System.out.println("\tHere's what you have to do:");
            for (int i = 0; i < list.size(); ++i) {
                System.out.printf("\t%d.%s\n", i + 1, list.get(i));
            }
        }
        Line.printLine();
    }

    private static void addToList(String input, Task.Type taskType) {
        String[] words = input.split(" ", 2);
        try {
            String description = words[1];
            Task task = new Task(description, taskType);
            list.add(task);
            System.out.printf("\tAlright your %s is in the list:\n\t%d.%s\n", taskType.toString(), list.size(), task);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.printf("\tWhat %s are you trying to add?\n\tGive me a description please.\n",
                    taskType.toString());
        }
        Line.printLine();
    }

    private static void markDone(boolean isDone, String input) {
        String[] words = input.split(" ", 2);
        try {
            int index = Integer.parseInt(words[1]) - 1;
            list.get(index).setDone(isDone);
            if (isDone)
                System.out.println("\tOk, this is done.");
            else
                System.out.println("\tOh, wait i guess this isn't done yet.");
            System.out.printf("\t%d.%s\n", index + 1, list.get(index));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\tWhat are you trying to mark here?\n\tI need the index of the task.");
        } catch (NumberFormatException e) {
            System.out.printf("\t%s Is not a number.\n\tPut a number please.\n", words[1]);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("\tWhat? That doesn't exist yet.\n\tMark something thats already in!");
        }
        Line.printLine();
    }

    private static void deleteFromList(String input) {
        String[] words = input.split(" ", 2);
        try {
            int index = Integer.parseInt(words[1]) - 1;
            Task task = list.get(index);
            System.out.printf("\tOk that's removed:\n\t%d.%s\n", index + 1, task);
            list.remove(index);
            if (list.size() == 0)
                System.out.println("\tSweet thats all tasks gone!");
            else
                System.out.printf("\tYou have %d remaining things to do.\n", list.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.printf("\tWhat are you trying to delete?\n\tI need a number here.\n");
        } catch (NumberFormatException e) {
            System.out.printf("\t%s Is not a number.\n\tPut a number please.\n", words[1]);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("\tWhat? That doesn't exist yet.\n\tDelete something thats already in!");
        }
        Line.printLine();

    }

    public static void reply(String input) {
        String[] words = input.split(" ", 2);
        switch (words[0].toLowerCase()) {
            case "help" -> printHelp();
            case "list" -> printList();
            case "todo" -> addToList(input, Task.Type.TODO);
            case "deadline" -> addToList(input, Task.Type.DEADLINE);
            case "event" -> addToList(input, Task.Type.EVENT);
            case "mark" -> markDone(true, input);
            case "unmark" -> markDone(false, input);
            case "delete" -> deleteFromList(input);
            default -> throw new DougException();
        }
    }
}
