package doug.ui;

import java.util.ArrayList;
import java.util.Scanner;

import doug.task.Task;

public class Ui {
    private Scanner in;

    private final String name = "Doug";
    private final String line = "_______________________________________________________________________________________";
    private final String banner = " ____\n"
            + "|  _ \\  ___  _   _  __ _\n"
            + "| | | |/ _ \\| | | |/ _` |\n"
            + "| |_| | (_) | |_| | (_| |\n"
            + "|____/ \\___/ \\__,_|\\__, |\n"
            + "                   |___/\n";

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String readInput() {
        System.out.println();
        String input = in.nextLine();
        printLine();
        return input;
    }

    public void print(String text) {
        System.out.println("\t" + text.replace("\n", "\n\t"));
    }

    public void printLine() {
        print(line);
    }

    public void printWelcome() {
        printLine();
        print(banner);
        print(String.format(
                "Hey, this is %s%s Where we solve problems no one has.\n\nWhat can I do for you? Try out 'help'!",
                name, name));
        printLine();
    }

    public void printGoodbye() {
        print("Peace.");
        printLine();
    }

    public void printHelp() {
        print("Here are the list of commands");

        print("\n===== List Tasks =====");
        print("list");

        print("\n===== Adding Tasks =====");
        java.util.EnumSet.allOf(Task.Type.class)
                .forEach(type -> print(String.format("%-10s <description>", type.toString().toLowerCase())));

        print("\n===== Marking Tasks =====");
        print(String.format("%-10s <index>", "mark"));
        print(String.format("%-10s <index>", "unmark"));

        print("\n===== Deleting Tasks =====");
        print(String.format("%-10s <index>", "delete"));

        print("\n===== Termination =====");
        print("bye");
    }

    public void printTaskList(ArrayList<Task> taskList) {
        if (taskList.size() <= 0) {
            print("You haven't added anything yet. Get to it.");
        } else {
            print("Here's what's on your list:");
            for (int i = 0; i < taskList.size(); ++i) {
                print(String.format("%d.%s", i + 1, taskList.get(i)));
            }
        }
    }

    public void printTaskAdded(Task task, int taskListSize) {
        print(String.format("Alright your %s is in the list:\n%d.%s\nYou now have %d total tasks.",
                task.getTaskType().toString(), taskListSize, task, taskListSize));
    }

    public void printTaskDeleted(Task task, int taskListSize) {
        print(String.format("Sweet that's gone:\n%d.%s\nYou have %d remaining tasks.",
                taskListSize, task, taskListSize));
        if (taskListSize == 0)
            print("I guess that's it.");
    }

    public void printTaskMarked(Task task, int index, boolean isMarked) {
        if (isMarked)
            print("I guess that's done:");
        else
            print("Oh wait I guess this isn't done yet.");
        print(String.format("%d.%s", index, task));
    }

    public void printError(String errorMessage) {
        print("ERROR: " + errorMessage);
    }
}
