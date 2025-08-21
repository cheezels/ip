import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChatBot {

    List<String> tasks;

    public ChatBot() {
        this.tasks = new ArrayList<>();
    }

    public String getIntroMessage() {
        return """
                Hello! I'm Lee Kuan Yew, founding father of Singapore.
                How can I help my fellow countryman today?
                """;
    }

    public String getResponse(String input) {
        switch (input.toLowerCase()) {
            case "list":
                return getTaskList();

            default:
                tasks.add(input);
                return "I shall add: " + input;
        }
    }

    public String getTaskList() {
        if (tasks.isEmpty()) {
            return """
                    This is not a game of cards!
                    Your list is empty, get to work!
                    """;
        }

        StringBuilder sb = new StringBuilder("Your work awaits you!\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return sb.toString().trim();
    }

    public String getEndMessage() {
        return """
                Goodbye fellow countryman! Majulah Singapura!
                """;
    }
}
