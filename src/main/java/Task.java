public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDone(boolean input) {
        isDone = input;
    }
}
