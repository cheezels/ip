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
        return null;
    }

    public String getEndMessage() {
        return """
                Goodbye fellow countryman! Majulah Singapura!
                """;
    }
}
