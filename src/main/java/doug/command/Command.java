package doug.command;

import doug.errors.DougException;
import doug.storage.Storage;
import doug.task.TaskList;
import doug.ui.Ui;

public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DougException;

    public boolean isExit() {
        return isExit;
    }
}
