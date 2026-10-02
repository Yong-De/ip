package doug.task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(Type.EVENT, description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(Type.EVENT, isDone, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveFormat() {
        return String.format("E|%d|%s|%s|%s", isDone ? 1 : 0, description, from, to);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
