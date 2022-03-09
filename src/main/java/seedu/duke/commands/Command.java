package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.tasks.ModuleList;

/**
 * Parent class of all commands in Mod Happy.
 */
public abstract class Command {
    protected static final String LS = System.lineSeparator();

    public abstract CommandResult execute(ModuleList moduleList) throws ModHappyException;
}