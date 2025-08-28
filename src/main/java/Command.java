public abstract class Command {
    public abstract void execute();

    public boolean checkExit() {
        return false;
    }
}
