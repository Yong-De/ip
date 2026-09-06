package doug.ui;

import doug.errors.DougException;
import doug.task.TaskManager;
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
            System.out.println("\tHey don't try to be funny!");
            Line.printLine();
        }
        return true;
    }

    private static void termination() {
        Line.printOutro();
        Line.printLine();
    }

}
