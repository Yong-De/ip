package doug.command;

import doug.task.TaskList;
import doug.ui.Ui;
import doug.storage.Storage;
import doug.errors.DougException;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DougException {
        ui.printTaskList(taskList.getTaskList());
    }
}
