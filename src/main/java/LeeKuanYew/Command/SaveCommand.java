package LeeKuanYew.Command;

import LeeKuanYew.Task.TaskList;
import LeeKuanYew.Ui;
import LeeKuanYew.Storage;
import java.io.IOException;

public class SaveCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.save(taskList);
            return ui.showMessage("It has been written down.");
        } catch (IOException e) {
            return ui.showMessage(e.getMessage());
        }
    }
}
