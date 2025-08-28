public class MarkCommand extends Command {

    private int index;
    private boolean mark;

    public MarkCommand(int index, boolean mark) {
        this.index = index;
        this.mark = mark;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (index < 0) {
            ui.showMessage("No negative numbers! Don't be stupid.");
        }

        if (index >= taskList.size()) {
            ui.showMessage("You overestimate your workload. Get to work!");
        }

        Task task = taskList.get(index);
        if (mark) {
            task.markDone();
            ui.showMessage("You have the IRON in you! Good job!\n" + task);
        } else {
            task.unmarkDone();
            ui.showMessage("No shame in failure. Pick yourself up, Singapore needs you.\n" + task);
        }
    }

}
