package doug.command;

import doug.task.Task;
import doug.task.TaskList;
import doug.ui.Ui;
import doug.storage.Storage;

public class AddCommand extends Command {
    private Task taskToAdd;

    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws doug.errors.DougException {
        taskList.addTask(taskToAdd);
        ui.printTaskAdded(taskToAdd, taskList.size());
        storage.save(taskList.getTaskList());
    };
}
