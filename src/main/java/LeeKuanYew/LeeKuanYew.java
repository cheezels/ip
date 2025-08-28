package

public class LeeKuanYew {

    private Storage storage;
    private Ui ui;
    private ChatBot chatBot;
    private TaskList taskList;

    public LeeKuanYew(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.chatBot = new ChatBot();
        this.taskList = new TaskList();
    }

    public void run() {
        ui.showIntroMessage();
        boolean exit = false;

        while (!exit) {
            try {
                String input = ui.getInput();
                Command c = chatBot.parseCommand(input);
                c.execute(taskList, ui, storage);
                exit = c.checkExit();
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new LeeKuanYew("data/LeeKaunYew.txt").run();
    }
}