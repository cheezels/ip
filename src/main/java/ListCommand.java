public class ListCommand extends Command {

    @Override
    public void execute (TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() == 0) {
            ui.showMessage("""
                    This is not a game of cards!
                    Your list is empty, get to work!""");
            return;
        }

        StringBuilder sb = new StringBuilder("Your work awaits you!\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            char isDone = task.checkDone() ? 'X' : ' ';
            sb.append((i + 1) + ". " + task.toString() + "\n");
        }

        ui.showMessage(sb.toString().trim());
    }
}
