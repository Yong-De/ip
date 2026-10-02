package doug.main;

import java.util.ArrayList;

import doug.command.Command;
import doug.errors.DougException;
import doug.parser.Parser;
import doug.storage.Storage;
import doug.task.TaskList;
import doug.ui.Ui;

public class Doug {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Doug(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DougException e) {
            ui.printError("INIT ERROR: " + e.getMessage());
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = Parser.parseCommand(input);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DougException e) {
                ui.printError("REPLY ERROR: " + e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Doug("data/task_list.txt").run();
    }
}
