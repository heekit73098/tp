package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;


/**
 * This Parser supports all commands which do not accept any additional arguments or parameters.
 */
public class NoArgumentParser extends Parser {
    private final String myCommandWord;

    public NoArgumentParser(String commandWord) {
        myCommandWord = commandWord;
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        // NoArgumentParser commands strictly take no input.
        if (userInput.length() != 0) {
            throw new ParseException();
        }
        switch (myCommandWord) {
        case (EXIT_COMMAND_WORD):
            return new ExitCommand();
        case (LIST_COMMAND_WORD):
            return new ListCommand();
        default:
            throw new ParseException();
        }
    }
}