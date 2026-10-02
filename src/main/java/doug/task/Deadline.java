package doug.task;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(Type.DEADLINE, description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(Type.DEADLINE, isDone, description);
        this.by = by;
    }

    @Override
    public String toSaveFormat() {
        // Appends the specific 'by' date to the save string
        return String.format("D|%d|%s|%s", isDone ? 1 : 0, description, by);
    }

    @Override
    public String toString() {
        // Calls the parent's string output [D][ ] description, then adds the date!
        return super.toString() + " (by: " + by + ")";
    }
}
