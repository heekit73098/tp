package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class UnknownException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_CATCH_UNKNOWN_EXCEPTION;

    public UnknownException(String userInput) {
        super(String.format(ERROR_MESSAGE, userInput));
    }
}
