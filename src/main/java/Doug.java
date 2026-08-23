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
        DougLines.printLine();
        DougLines.printBanner();
        DougLines.printIntro();
        DougLines.printLine();
    }

    private static boolean chatting() {
        System.out.println();
        String line;
        line = in.nextLine();
        DougLines.printLine();
        if (line.equalsIgnoreCase("bye")) {
            return false;
        }
        DougMethods.reply(line);
        return true;
    }

    private static void termination() {
        DougLines.printOutro();
        DougLines.printLine();
    }

}
