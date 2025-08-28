public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.get(index);
        taskList.remove(index);
        ui.showMessage(
                "I've spent my whole lifetime building this. Sadly you won't. Removing:\n" +
                "  " + task + "\n" +
                "Singapore urges you to do your remaining " + taskList.size() + " tasks"
        );
    }
}
