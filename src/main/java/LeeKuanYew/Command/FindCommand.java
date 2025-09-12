package LeeKuanYew.Command;

import LeeKuanYew.Storage;
import LeeKuanYew.Task.TaskList;
import LeeKuanYew.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute (TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() == 0) {
            return ui.showMessage("""
                    This is not a game of cards!
                    Your list is empty, get to work!""");
        }

        TaskList result = taskList.find(keyword);
        ListCommand c = new ListCommand();
        return c.execute(result, ui, storage);
    }

}
