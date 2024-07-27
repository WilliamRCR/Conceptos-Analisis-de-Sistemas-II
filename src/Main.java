import Application.TaskService;
import Presentation.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        TaskManager taskManager = new TaskManager(taskService);
        taskManager.start();
    }
}