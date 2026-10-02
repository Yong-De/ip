package doug.task;

import java.util.ArrayList;

import doug.errors.DougException;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> loadedTaskList) {
        this.taskList = loadedTaskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task markTask(int index, boolean isMarked) {
        if (index < 0 || index >= taskList.size())
            throw new DougException("What? That doesn't exist yet!");
        Task toMark = taskList.get(index);
        toMark.setDone(isMarked);
        return toMark;
    }

    public Task deleteTask(int index) {
        if (index < 0 || index >= taskList.size())
            throw new DougException("Delete something thats already in!");
        Task toDelete = taskList.get(index);
        taskList.remove(index);
        return toDelete;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= taskList.size())
            throw new DougException("Can't get that. Not a thing.");
        return taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }
}
