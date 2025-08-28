import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ChatBot {

    private List<Task> tasks;
    private Storage storage;

    public ChatBot() {
        this.storage = new Storage("./data/LeeKuanYew.txt");
        try {
            this.tasks = storage.load();
        } catch (IOException e) {
            this.tasks = new ArrayList<>();
        }
    }

    public String getIntroMessage() {
        return """
                Hello! I'm Lee Kuan Yew, founding father of Singapore.
                How can I help my fellow countryman today?""";
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

            case "todo":
                ToDoTask toDo = new ToDoTask(parts[1]);
                tasks.add(toDo);
                return "I shall add:\n" +
                        "  " + toDo + "\n" +
                        "Singapore needs you to complete your " + tasks.size() + " tasks";

            case "deadline":
                String[] deadlineParts = parts[1].split("/", 2);
                if (deadlineParts.length != 2) return "Discipline requires deadlines.";

                // Invalid date format check
                try {
                    DeadlineTask deadlineTask = new DeadlineTask(deadlineParts[0], deadlineParts[1]);
                    tasks.add(deadlineTask);

                    return "I shall add:\n" +
                            "  " + deadlineTask + "\n" +
                            "Singapore needs you to complete your " + tasks.size() + " tasks";

                } catch (DateTimeParseException e) {
                    return "Date must be in yyyy-mm-dd format.";
                }

            case "event":
                String[] eventParts = parts[1].split("/", 3);
                if (eventParts.length != 3) return "It takes time to build a country like Singapore.";

                EventTask eventTask = new EventTask(eventParts[0], eventParts[1], eventParts[2]);
                tasks.add(eventTask);

                return "I shall add:\n" +
                        "  " + eventTask + "\n" +
                        "Singapore needs you to complete your " + tasks.size() + " tasks";

            case "delete":
                try {
                    int taskNum = Integer.parseInt(parts[1]) - 1;

                    if (taskNum < 0) {
                        return "No negative numbers! Don't be stupid.";
                    }

                    if (taskNum >= tasks.size()) {
                        return "You overestimate your workload. Get to work!";
                    }

                    Task task = tasks.get(taskNum);
                    tasks.remove(taskNum);

                    return "I've spent my whole lifetime building this. Sadly you won't. Removing:\n" +
                           "  " + task + "\n" +
                           "Singapore urges you to do your remaining " + tasks.size() + " tasks";
                } catch (NumberFormatException e) {
                    return "You must specify a task number!";
                }
            case "save":
                try {
                    this.storage.save(this.tasks);
                    return "It has been written down.";
                } catch (IOException e) {
                    return "Error in saving file.";
                }
            default:
                return "BE CONCISE WITH YOUR WORDS.";
        }
    }

    public Command parseCommand(String input) throws Exception {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case "bye":
            case "list":
            case "mark":
            case "unmark":
            case "todo":
            case "deadline":
            case "event":
            default:
                throw new Exception("YOUR WORDS MEAN NOTHING!");
        }

    }

    public String getTaskList() {
        if (tasks.isEmpty()) {
            return """
                    This is not a game of cards!
                    Your list is empty, get to work!""";
        }

        StringBuilder sb = new StringBuilder("Your work awaits you!\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            char isDone = task.checkDone() ? 'X' : ' ';
            sb.append((i + 1) + ". " + task.toString() + "\n");
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
                Goodbye fellow countryman! Majulah Singapura!""";
    }
}
