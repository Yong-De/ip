package doug.task;

import doug.errors.DougException;

public abstract class Task {
    public enum Type {
        TODO, DEADLINE, EVENT
    };

    protected Type taskType;
    protected boolean isDone;
    protected String description;

    public Task(Type taskType, String description) {
        this.taskType = taskType;
        this.isDone = false;
        this.description = description;
    }

    public Task(Type taskType, Boolean isDone, String description) {
        this.taskType = taskType;
        this.isDone = isDone;
        this.description = description;
    }

    private static Type resolveTaskType(String taskChar) throws DougException {
        switch (taskChar) {
            case "T":
                return Task.Type.TODO;
            case "D":
                return Task.Type.DEADLINE;
            case "E":
                return Task.Type.EVENT;
            default:
                throw new DougException("Corrupted TaskType");
        }
    }

    private static boolean resolveIsDone(String isDoneChar) {
        if (!isDoneChar.equals("0") && !isDoneChar.equals("1"))
            throw new DougException("Corrupted isDone");
        return isDoneChar.equals("1");
    }

    public static Task fromSaveFormat(String input) {
        String[] taskParams = input.split("\\|");
        if (taskParams.length < 3)
            throw new DougException("Save file corrupted: Not enough arguments.");

        Type parsedTaskType = resolveTaskType(taskParams[0]);
        boolean parsedIsDone = resolveIsDone(taskParams[1]);
        String parsedDescription = taskParams[2];

        try {
            switch (parsedTaskType) {
                case TODO:
                    return new Todo(parsedDescription, parsedIsDone);
                case DEADLINE:
                    return new Deadline(parsedDescription, taskParams[3], parsedIsDone);
                case EVENT:
                    return new Event(parsedDescription, taskParams[3], taskParams[4], parsedIsDone);
                default:
                    throw new DougException("Unknown task type.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DougException("Save file corrupted: Missing date arguments.");
        }
    }

    public abstract String toSaveFormat();

    public String getDescription() {
        return description;
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
        return String.format("[%c][%c] %s", taskTypeChar, isDoneChar, getDescription());
    }
}
