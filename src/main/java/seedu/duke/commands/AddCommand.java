package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;


public class AddCommand extends Command {
    private static final String ADD_TASK_MESSAGE = "Hey! I have added this task under %s!" + LS + "%s" + LS
            + "Now you have %d task(s) in your list!" + LS;
    private static final String ADD_MODULE_MESSAGE = "Hey! I have added this module!" + LS + "%s";
    private static final String MODULE_ALREADY_EXISTS = "A module with that name already exists...";

    private final boolean isAddTask;
    private Task newTask;
    private Module newModule;

    public AddCommand(String name, String description, boolean isTask) {
        if (isTask) {
            newTask = new Task(name, description);
            isAddTask = true;
        } else {
            newModule = new Module(name, description);
            isAddTask = false;
        }
    }

    @Override
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        String res = "";
        if (isAddTask) {
            // TODO: change this once support for -mod is implemented
            Module targetModule = moduleList.getGeneralTasks();
            TaskList taskList = targetModule.getTaskList();
            res = String.format(ADD_TASK_MESSAGE, targetModule, taskList.addTask(newTask), taskList.size());
        } else {
            if (!moduleList.isModuleExists(newModule.getModuleCode())) {
                moduleList.addModule(newModule);
                res = String.format(ADD_MODULE_MESSAGE, newModule);
            } else {
                res = MODULE_ALREADY_EXISTS;
            }
        }
        return new CommandResult(res);
    }
}
