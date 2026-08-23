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
        Method.reply(line);
        return true;
    }

    private static void termination() {
        Line.printOutro();
        Line.printLine();
    }

}
