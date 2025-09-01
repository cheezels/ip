package LeeKuanYew.Command;

import java.util.List;

import LeeKuanYew.Storage;
import LeeKuanYew.Task.Task;
import LeeKuanYew.Task.TaskList;
import LeeKuanYew.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute (TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() == 0) {
            ui.showMessage("""
                    This is not a game of cards!
                    Your list is empty, get to work!""");
            return;
        }

        TaskList result = taskList.find(keyword);
        ListCommand c = new ListCommand();
        c.execute(result, ui, storage);
    }

}
