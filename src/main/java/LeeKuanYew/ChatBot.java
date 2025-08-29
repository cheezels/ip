package LeeKuanYew;

import LeeKuanYew.Command.Command;
import LeeKuanYew.Command.ByeCommand;
import LeeKuanYew.Command.ListCommand;
import LeeKuanYew.Command.MarkCommand;
import LeeKuanYew.Command.DeleteCommand;
import LeeKuanYew.Command.SaveCommand;
import LeeKuanYew.Command.ToDoCommand;
import LeeKuanYew.Command.DeadlineCommand;
import LeeKuanYew.Command.EventCommand;

public class ChatBot {

    public Command parseCommand(String input) throws Exception {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case "bye":
                return new ByeCommand();

            case "list":
                return new ListCommand();

            case "mark":
                if (parts.length < 2) {
                    throw new Exception("You must specify a task number!");
                }

                try {
                    int i = Integer.parseInt(parts[1]) - 1;
                    return new MarkCommand(i, true);
                } catch (NumberFormatException e) {
                    throw new Exception("This is not a number.");
                }

            case "unmark":
                if (parts.length < 2) {
                    throw new Exception("You must specify a task number!");
                }

                try {
                    int j = Integer.parseInt(parts[1]) - 1;
                    return new MarkCommand(j, false);
                } catch (NumberFormatException e) {
                    throw new Exception("This is not a number.");
                }

            case "delete":
                if (parts.length < 2) {
                    throw new Exception("You must specify a task number!");
                }

                try {
                    int k = Integer.parseInt(parts[1]) - 1;
                    return new DeleteCommand(k);
                } catch (NumberFormatException e) {
                    throw new Exception("This is not a number.");
                }

            case "save":
                return new SaveCommand();

            case "todo":
                return new ToDoCommand(parts[1]);

            case "deadline":
                String[] deadlineParts = parts[1].split("/", 2);
                if (deadlineParts.length != 2) throw new Exception("Discipline requires deadlines.");

                return new DeadlineCommand(deadlineParts[0], deadlineParts[1]);

            case "event":
                String[] eventParts = parts[1].split("/", 3);
                if (eventParts.length != 3) throw new Exception("It takes time to build a country like Singapore.");

                return new EventCommand(eventParts[0], eventParts[1], eventParts[2]);

            default:
                throw new Exception("YOUR WORDS MEAN NOTHING!");
        }

    }
}
