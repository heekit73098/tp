package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.GeneralParseException;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleList;
import seedu.duke.data.Task;
import seedu.duke.data.TaskList;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;

public class TagCommand extends Command {

    private static final String ADD_TAG = StringConstants.ADD_COMMAND_WORD;
    private static final String DEL_TAG = StringConstants.DELETE_COMMAND_WORD;
    private static final String ADD_TAG_MESSAGE = StringConstants.ADD_TAG_MESSAGE;
    private static final String DEL_TAG_MESSAGE = StringConstants.DEL_TAG_MESSAGE;

    private final String tagOperation;
    private final int taskIndex;
    private final String taskModule;
    private final String tagDescription;
    private String result;

    public int getTaskIndex() {
        return taskIndex;
    }

    public String getTagOperation() {
        return tagOperation;
    }

    public String getTaskModule() {
        return taskModule;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public TagCommand(String tagOperation, int taskIndex, String taskModule, String tagDescription) {
        this.tagOperation = tagOperation;
        this.taskIndex = taskIndex;
        this.taskModule = taskModule;
        this.tagDescription = tagDescription;
    }

    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws ModHappyException {
        Module targetModule;
        if (Objects.isNull(taskModule)) {
            targetModule = moduleList.getGeneralTasks();
        } else {
            targetModule = moduleList.getModule(taskModule);
        }
        switch (tagOperation) {
        case ADD_TAG:
            addTag(targetModule);
            return new CommandResult(result);
        case DEL_TAG:
            removeTag(targetModule);
            return new CommandResult(result);
        default:
            throw new GeneralParseException();
        }
    }

    /**
     * Adds a tag to a task.
     *
     * @param targetModule Module that contains the task to be tagged.
     */
    private void addTag(Module targetModule) throws ModHappyException {
        TaskList taskList = targetModule.getTaskList();
        result  = String.format(ADD_TAG_MESSAGE, tagDescription, taskList.addTag(tagDescription, taskIndex));
    }

    /**
     * Removes a tag from a task.
     *
     * @param targetModule Module that contains the task with the tag to be removed
     */
    private void removeTag(Module targetModule) throws ModHappyException {
        TaskList taskList = targetModule.getTaskList();
        Task task = taskList.removeTag(tagDescription, taskIndex);
        result = String.format(DEL_TAG_MESSAGE, tagDescription, task);
    }
}
