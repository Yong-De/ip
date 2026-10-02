package doug.storage;

import doug.errors.DougException;
import doug.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path dataPath;

    public Storage(String filePath) {
        this.dataPath = Path.of(filePath);
    }

    public ArrayList<Task> load() throws DougException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            if (dataPath.getParent() != null)
                Files.createDirectories(dataPath.getParent());

            if (!Files.exists(dataPath))
                Files.createFile(dataPath);

            List<String> savedLines = Files.readAllLines(dataPath);
            for (int i = 0; i < savedLines.size(); ++i) {
                Task task = Task.fromSaveFormat(savedLines.get(i));
                taskList.add(task);
            }

            return taskList;

        } catch (IOException e) {
            throw new DougException("Could not read save file.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DougException("Save file corrupted.");
        }
    }

    public void save(ArrayList<Task> taskList) {
        try {
            if (dataPath.getParent() != null)
                Files.createDirectories(dataPath.getParent());

            if (!Files.exists(dataPath))
                Files.createFile(dataPath);

            ArrayList<String> saveLines = new ArrayList<>();
            for (int i = 0; i < taskList.size(); ++i) {
                String saveFormat = taskList.get(i).toSaveFormat();
                saveLines.add(saveFormat);
            }

            Files.write(dataPath, saveLines);

        } catch (IOException e) {
            throw new DougException("Failed to save tasks.");
        }

    }
}
