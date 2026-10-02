package doug.command;

import doug.task.Task;
import doug.task.TaskList;
import doug.storage.Storage;
import doug.errors.DougException;
import doug.ui.Ui;

public class MarkCommand extends Command {
    private int indexToMark;
    private boolean isMarked;

    public MarkCommand(int index, boolean isMarked) {
        this.indexToMark = index;
        this.isMarked = isMarked;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DougException {
        Task markedTask = taskList.markTask(indexToMark, isMarked);
        ui.printTaskMarked(markedTask, indexToMark + 1, isMarked);
        storage.save(taskList.getTaskList());
    }
}
