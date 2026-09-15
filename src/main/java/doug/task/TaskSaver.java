import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import doug.errors.DougException;

public class TaskSaver {
    private static final Path DATA_PATH = Path.of("data", "task_list.txt");

    public static void createSaveFile(ArrayList<Task> taskList) {
        try {
            if (DATA_PATH.getParent() != null)
                Files.createDirectories(DATA_PATH.getParent());

            if (!Files.exists(DATA_PATH))
                Files.createFile(DATA_PATH);

            for (int i = 0; i < taskList.size(); ++i) {
                Task task = taskList.get(i);
                String text = String.format("%c|%d|%s\n", task.getTaskType().toString().charAt(0), task.getIsDone(),
                        task.getDescription());
                Files.writeString(DATA_PATH, text);
            }

        } catch (IOException e) {
            throw new DougException("\tFailed tp save tasks: " + e.getMessage());
        }
    }
}
