package LeeKuanYew.Command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import LeeKuanYew.Storage;
import LeeKuanYew.Task.DeadlineTask;
import LeeKuanYew.Task.EventTask;
import LeeKuanYew.Task.Task;
import LeeKuanYew.Task.TaskList;
import LeeKuanYew.Ui;

public class UpdateCommand extends Command {
    private int index;
    private String deadline;

    public UpdateCommand(int index, String deadline) {
        this.index = index;
        this.deadline = deadline;
    }

    @Override
    public String execute (TaskList taskList, Ui ui, Storage storage) {
        if (index < 0) {
            return ui.showMessage("No negative numbers! Don't be stupid.");
        }

        if (index >= taskList.size()) {
            return ui.showMessage("You overestimate your workload. Get to work!");
        }

        Task task = taskList.get(index);
        if (task instanceof DeadlineTask) {
            try {
                ((DeadlineTask) task).updateDeadline(deadline);
                return "Updated: " + task;
            } catch (DateTimeParseException e) {
                return ui.showMessage("penis");
            }
        } else {
            return ui.showMessage("ERROR SOMETHING WENT WRONG.");
        }
    }
}
