public class LeeKuanYew {

    private Storage storage;
    private Ui ui;
    private ChatBot chatBot;

    public LeeKuanYew(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.chatBot = new ChatBot();
    }

    public void run() {
        ui.showIntroMessage();
        boolean exit = false;

        while (!exit) {
            String input = ui.getInput();
            Command c = chatBot.parseCommand(input);
        }
    }

    public static void main(String[] args) {
        new LeeKuanYew("data/LeeKaunYew.txt").run();
    }
}