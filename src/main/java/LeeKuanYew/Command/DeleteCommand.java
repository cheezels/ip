package LeeKuanYew.Command;

import LeeKuanYew.Ui;
import LeeKuanYew.Storage;
import LeeKuanYew.Task.Task;
import LeeKuanYew.Task.TaskList;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (index < 0) {
            return ui.showMessage("No negative numbers! Don't be stupid.");
        }

        if (index >= taskList.size()) {
            return ui.showMessage("You overestimate your workload. Get to work!");
        }

        Task task = taskList.get(index);
        taskList.remove(index);
        return ui.showMessage(
                "I've spent my whole lifetime building this. Sadly you won't. Removing:\n" +
                "  " + task + "\n" +
                "Singapore urges you to do your remaining " + taskList.size() + " tasks"
        );
    }
}
