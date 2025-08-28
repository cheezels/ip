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
            Task task = taskList.get(index);
            task.markDone();
            ui.showMessage("You have the IRON in you! Good job!\n" + task);
        } else {
            Task task = taskList.get(index);
            task.unmarkDone();
            ui.showMessage("No shame in failure. Pick yourself up, Singapore needs you.\n" + task);
        }
    }

}
