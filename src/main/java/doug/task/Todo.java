package doug.task;

public class Todo extends Task {

    public Todo(String description) {
        super(Type.TODO, description);
    }

    public Todo(String description, boolean isDone) {
        super(Type.TODO, isDone, description);
    }

    @Override
    public String toSaveFormat() {
        return String.format("T|%d|%s", isDone ? 1 : 0, description);
    }
}
