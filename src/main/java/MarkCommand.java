public class MarkCommand extends Command {

    private int index;
    private boolean mark;

    public MarkCommand(int index, boolean mark) {
        this.index = index;
        this.mark = mark;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (mark) {
            taskList.get(index).markDone();
        } else {
            taskList.get(index).unmarkDone();
        }
    }

}
