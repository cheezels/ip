public class ToDoCommand extends Command {

    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ToDoTask task = new ToDoTask(description);
        taskList.add(task);
        ui.showMessage(
                "I shall add:\n" +
                "  " + task + "\n" +
                "Singapore needs you to complete your " + taskList.size() + " tasks"
        );
    }
}
