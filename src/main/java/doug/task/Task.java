package doug.task;

public class Task {
    public enum Type {
        TODO, DEADLINE, EVENT
    };

    private String name;
    private Type taskType;
    private boolean isDone;

    public Task(String name, Type taskType) {
        this.name = name;
        this.taskType = taskType;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public Type getTaskType() {
        return taskType;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDone(boolean input) {
        isDone = input;
    }

    @Override
    public String toString() {
        char taskTypeChar = taskType.toString().charAt(0);
        char isDoneChar = (isDone) ? 'X' : ' ';
        return String.format("[%c][%c] %s", taskTypeChar, isDoneChar, getName());
    }
}
