import java.io.IOException;

public class SaveCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.save(taskList);
            ui.showMessage("It has been written down.");
        } catch (IOException e) {
            ui.showMessage("Error in saving file.");
        }
    }
}
