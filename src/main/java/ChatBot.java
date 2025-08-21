import com.sun.source.util.TaskListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChatBot {

    List<Task> tasks;

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
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        switch (command) {
            case "list":
                return getTaskList();

            case "mark":
                return markTask(parts, true);

            case "unmark":
                return markTask(parts, false);

            default:
                Task task = new Task(input);
                tasks.add(task);
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
            Task task = tasks.get(i);
            char isDone = task.checkDone() ? 'X' : ' ';
            sb.append((i + 1) + task.toString() + "\n");
        }
        return sb.toString().trim();
    }

    public String markTask(String[] parts, boolean mark) {
        // check if there is anything after mark
        if (parts.length < 2) return "You must specify a task number!";

        try {
            int taskNum = Integer.parseInt(parts[1]) - 1;

            if (taskNum < 0) {
                return "No negative numbers! Don't be stupid.";
            }

            if (taskNum >= tasks.size()) {
                return "You overestimate your workload. Get to work!";
            }

            Task task = tasks.get(taskNum);
            if (mark) {
                task.markDone();
                return "You have the IRON in you! Good job!\n" + task;

            } else {
                task.unmarkDone();
                return "No shame in failure. Pick yourself up, Singapore needs you.\n" + task;
            }
        } catch (NumberFormatException e) {
            return "That's not a valid number.";

        }
    }

    public String getEndMessage() {
        return """
                Goodbye fellow countryman! Majulah Singapura!
                """;
    }
}
