package seedu.duke.data;

import java.util.ArrayList;
import java.util.Objects;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.util.StringConstants;

public class Task {
    public static final String ICON_UNCOMPLETED = StringConstants.ICON_UNCOMPLETED;
    public static final String ICON_COMPLETED = StringConstants.ICON_COMPLETED;
    public static final String TASK_STRING_NO_DESC_NO_TIME = "%s %s %s";
    public static final String TASK_STRING_WITH_DESC_NO_TIME = "%s %s (%s) %s";
    public static final String TASK_STRING_NO_DESC_WITH_TIME = "%s %s ("
            + StringConstants.ESTIMATED_WORKING_TIME + "%s) %s";
    public static final String TASK_STRING_WITH_DESC_WITH_TIME = "%s %s (%s) ("
            + StringConstants.ESTIMATED_WORKING_TIME + "%s) %s";

    private boolean isTaskDone;
    private String taskName;
    private String taskDescription;
    private TaskDuration workingTime;
    private final ArrayList<String> tags;

    public Task(String taskName, String taskDescription, String workingTime) throws ModHappyException {

        this.taskName = taskName;
        this.taskDescription = taskDescription;
        if (!Objects.isNull(workingTime)) {
            this.workingTime = new TaskDuration(workingTime);
        } else {
            this.workingTime = null;
        }
        isTaskDone = false;
        tags = new ArrayList<>();

    }

    public ArrayList<String> getTagList() {
        return tags;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getWorkingTime() {
        if (Objects.isNull(workingTime)) {
            return null;
        } else {
            return workingTime.toString();
        }

    }

    public void setTaskDescription(String description) {
        taskDescription = description;
    }

    public void setWorkingTime(String workingTime) throws ModHappyException {
        this.workingTime = new TaskDuration(workingTime);
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**.
     * Check what are the tasks parameters input by user
     * @return Task parameters status
     */
    public TaskParameters getTaskParameterStatus() {
        boolean hasTaskDescriptionAndWorkingTime = (taskDescription != null && workingTime != null);
        boolean hasTaskDescriptionOnly = (taskDescription != null);
        boolean hasWorkingTimeOnly = (workingTime != null);
        if (hasTaskDescriptionAndWorkingTime) {
            return TaskParameters.DESCRIPTION_AND_WORKING_TIME;
        } else if (hasTaskDescriptionOnly) {
            return TaskParameters.DESCRIPTION_ONLY;
        } else if (hasWorkingTimeOnly) {
            return TaskParameters.WORKING_TIME_ONLY;
        } else {
            return TaskParameters.NO_DESCRIPTION_OR_WORKING_TIME;
        }
    }

    public boolean getTaskDone() {
        return isTaskDone;
    }

    /**
     * Sets the completion status of the task.
     * @param status new task completion status
     */
    public void setTaskDone(boolean status) {
        isTaskDone = status;
    }

    /**
     * Returns the task as a formatted string.
     */
    @Override
    public String toString() {
        String taskStatusString = isTaskDone ? ICON_COMPLETED : ICON_UNCOMPLETED;
        TaskParameters taskParameters = getTaskParameterStatus();
        switch (taskParameters) {
        case DESCRIPTION_AND_WORKING_TIME:
            return String.format(TASK_STRING_WITH_DESC_WITH_TIME, taskStatusString, taskName,
                    taskDescription, workingTime, tags);
        case DESCRIPTION_ONLY:
            return String.format(TASK_STRING_WITH_DESC_NO_TIME, taskStatusString, taskName, taskDescription, tags);
        case WORKING_TIME_ONLY:
            return String.format(TASK_STRING_NO_DESC_WITH_TIME, taskStatusString, taskName, workingTime, tags);
        default:
            return String.format(TASK_STRING_NO_DESC_NO_TIME, taskStatusString, taskName, tags);
        }
    }
}
