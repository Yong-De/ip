package doug.ui;

import doug.errors.DougException;
import doug.task.TaskManager;
import doug.task.TaskSaver;
import java.util.Scanner;

public class Doug {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        initialization();
        while (chatting())
            ;
        termination();
    }

    private static void initialization() {
        try {
            TaskSaver.loadSaveFile();
        } catch (DougException e) {
            System.out.println("\tINIT ERROR: " + e.getMessage());
            Line.printLine();
        }
        Line.printLine();
        Line.printBanner();
        Line.printIntro();
        Line.printLine();
    }

    private static boolean chatting() {
        System.out.println();
        String line;
        line = in.nextLine();
        Line.printLine();
        if (line.equalsIgnoreCase("bye")) {
            return false;
        }

        try {
            TaskManager.reply(line);
        } catch (DougException e) {
            System.out.println("\tREPLY ERROR: " + e.getMessage());
            Line.printLine();
        }
        return true;
    }

    private static void termination() {
        Line.printOutro();
        Line.printLine();
    }

}
