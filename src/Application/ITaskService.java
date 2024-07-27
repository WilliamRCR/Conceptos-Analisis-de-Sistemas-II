package Application;

import Data.Task;

import java.util.List;

public interface ITaskService {
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(int taskId);
    List<Task> getPendingTasks();
    List<Task> getCompletedTasks();
}
