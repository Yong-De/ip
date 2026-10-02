package doug.command;

import doug.task.TaskList;
import doug.ui.Ui;
import doug.storage.Storage;
import doug.errors.DougException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DougException {
        ui.printGoodbye();
        super.isExit = true;
    }
}
