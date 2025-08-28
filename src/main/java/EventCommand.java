import java.time.format.DateTimeParseException;

public class EventCommand extends Command {

    private String description;
    private String startDate;
    private String endDate;

    public EventCommand(String description, String startDate, String endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        EventTask task = new EventTask(description, startDate, endDate);
        taskList.add(task);
        ui.showMessage(
                "I shall add:\n" +
                "  " + task + "\n" +
                "Singapore needs you to complete your " + taskList.size() + " tasks"
        );
    }
}
