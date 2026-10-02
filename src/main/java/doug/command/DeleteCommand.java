package doug.command;

import doug.task.Task;
import doug.task.TaskList;
import doug.ui.Ui;
import doug.storage.Storage;
import doug.errors.DougException;

public class DeleteCommand extends Command {
    private int indexToDelete;

    public DeleteCommand(int index) {
        this.indexToDelete = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DougException {
        Task deletedTask = taskList.deleteTask(indexToDelete);
        ui.printTaskDeleted(deletedTask, indexToDelete + 1);
        storage.save(taskList.getTaskList());
    };
}
