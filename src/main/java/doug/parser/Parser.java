package doug.parser;

import doug.command.AddCommand;
import doug.command.Command;
import doug.command.DeleteCommand;
import doug.command.ExitCommand;
import doug.command.FindCommand;
import doug.command.HelpCommand;
import doug.command.ListCommand;
import doug.command.MarkCommand;
import doug.errors.DougException;
import doug.task.Deadline;
import doug.task.Event;
import doug.task.Task;
import doug.task.Todo;

public class Parser {
    public static Task parseTodo(String input) throws DougException {
        String[] words = input.split(" ", 2);

        if (words.length < 2 || words[1].trim().isEmpty())
            throw new DougException("Give a description for the task please.");

        String description = words[1];
        return new Todo(description);
    }

    public static Task parseDeadline(String input) throws DougException {
        String[] words = input.split(" ", 2);

        if (words.length < 2 || words[1].trim().isEmpty())
            throw new DougException("Give a description for the deadline please.");

        String input2 = words[1];
        String[] words2 = input2.split(" /by ");

        if (words2.length < 2 || words2[1].trim().isEmpty())
            throw new DougException("Deadline needs a '/by' date!");

        String description = words2[0];
        String byDateString = words2[1];

        return new Deadline(description, byDateString);
    }

    public static Task parseEvent(String input) throws DougException {
        String[] words = input.split(" ", 2);

        if (words.length < 2 || words[1].trim().isEmpty())
            throw new DougException("Give a description for the event please.");

        String input2 = words[1];
        String[] words2 = input2.split(" /from ");

        if (words2.length < 2 || words2[1].trim().isEmpty())
            throw new DougException("Event needs a '/from' date!");

        String description = words2[0];
        String input3 = words2[1];
        String[] words3 = input3.split(" /to ");

        if (words3.length < 2 || words3[1].trim().isEmpty())
            throw new DougException("Event needs a '/from' date!");

        String fromDateString = words3[0];
        String toDateString = words3[1];

        return new Event(description, fromDateString, toDateString);
    }

    public static Command parseCommand(String input) throws DougException {
        String[] words = input.split(" ", 2);
        String commandWord = words[0].toLowerCase();

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "help":
                return new HelpCommand();
            case "todo":
                Task newTodo = parseTodo(input);
                return new AddCommand(newTodo);
            case "deadline":
                Task newDeadline = parseDeadline(input);
                return new AddCommand(newDeadline);
            case "event":
                Task newEvent = parseEvent(input);
                return new AddCommand(newEvent);
            case "mark":
                try {
                    int indexToMark = Integer.parseInt(words[1]) - 1;
                    return new MarkCommand(indexToMark, true);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DougException("I need the index of the task to mark.");
                } catch (NumberFormatException e) {
                    throw new DougException(String.format("%s is not a number. Put a number please.", words[1]));
                }
            case "unmark":
                try {
                    int indexToMark = Integer.parseInt(words[1]) - 1;
                    return new MarkCommand(indexToMark, false);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DougException("I need the index of the task to unmark.");
                } catch (NumberFormatException e) {
                    throw new DougException(String.format("%s is not a number. Put a number please.", words[1]));
                }
            case "find":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new DougException("What are you trying to find? Give me a keyword.");
                }
                return new FindCommand(words[1].trim());
            case "delete":
                try {
                    int indexToDelete = Integer.parseInt(words[1]) - 1;
                    return new DeleteCommand(indexToDelete);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DougException("What are you trying to delete? I need a number here.");
                } catch (NumberFormatException e) {
                    throw new DougException(String.format("%s Is not a number. Put a number please.", words[1]));
                }
            default:
                throw new DougException("Hey don't try to be funny! Get some help with 'help'!");
        }
    }
}
