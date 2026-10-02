package doug.command;

import java.util.ArrayList;

import doug.storage.Storage;
import doug.task.Task;
import doug.task.TaskList;
import doug.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = taskList.findTasks(keyword);
        ui.printFoundTasks(foundTasks);
    }
}
