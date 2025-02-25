package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class InvalidFlagException extends GeneralParseException {
    private static final String ERROR_STRING_INVALID = StringConstants.ERROR_INVALID_FLAG;
    private static final String ERROR_STRING_MISSING = StringConstants.ERROR_MISSING_FLAG;

    public InvalidFlagException() {
        super(ERROR_MESSAGE + ERROR_STRING_MISSING);
    }

    public InvalidFlagException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING_INVALID, error));
    }

}
