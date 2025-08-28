public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage("""
                Goodbye fellow countryman! Majulah Singapura!""");
    }

    @Override
    public boolean checkExit() {
        return true;
    }
}
