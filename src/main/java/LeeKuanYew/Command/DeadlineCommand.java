package LeeKuanYew.Command;

import LeeKuanYew.Ui;
import LeeKuanYew.Storage;
import LeeKuanYew.Task.DeadlineTask;
import LeeKuanYew.Task.TaskList;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    private String description;
    private String deadline; // let DateTime conversion and any exception throwing be done by LeeKuanYew.Task.DeadlineTask class

    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            DeadlineTask task = new DeadlineTask(description, deadline);
            taskList.add(task);
            ui.showMessage(
                    "I shall add:\n" +
                            "  " + task + "\n" +
                            "Singapore needs you to complete your " + taskList.size() + " tasks"
            );
        } catch (DateTimeParseException e) {
            ui.showMessage("Date must be in yyyy-mm-dd format.");
        }
    }
}
