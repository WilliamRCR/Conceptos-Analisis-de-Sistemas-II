package Presentation;

import Application.ITaskService;
import Data.Task;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private final ITaskService taskService;

    public TaskManager(ITaskService taskService) {
        this.taskService = taskService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Gestión de Tareas");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Modificar tarea");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Ver tareas pendientes");
            System.out.println("5. Ver tareas completadas");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    addTask(scanner);
                    break;
                case "2":
                    updateTask(scanner);
                    break;
                case "3":
                    deleteTask(scanner);
                    break;
                case "4":
                    viewPendingTasks();
                    break;
                case "5":
                    viewCompletedTasks();
                    break;
                case "6":
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }

    private void addTask(Scanner scanner) {
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Descripción: ");
        String description = scanner.nextLine();

        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setCompleted(false);
        taskService.addTask(task);
        System.out.println("Tarea agregada.");
    }

    private void updateTask(Scanner scanner) {
        System.out.print("ID de la tarea: ");
        int id = Integer.parseInt(scanner.nextLine());

        Task task = new Task();
        task.setId(id);
        System.out.print("Nombre: ");
        task.setName(scanner.nextLine());
        System.out.print("Descripción: ");
        task.setDescription(scanner.nextLine());
        System.out.print("¿Completada? (true/false): ");
        task.setCompleted(Boolean.parseBoolean(scanner.nextLine()));

        taskService.updateTask(task);
        System.out.println("Tarea actualizada.");
    }

    private void deleteTask(Scanner scanner) {
        System.out.print("ID de la tarea: ");
        int id = Integer.parseInt(scanner.nextLine());
        taskService.deleteTask(id);
        System.out.println("Tarea eliminada.");
    }

    private void viewPendingTasks() {
        List<Task> tasks = taskService.getPendingTasks();
        System.out.println("Tareas Pendientes:");
        for (Task task : tasks) {
            System.out.println("ID: " + task.getId() + ", Nombre: " + task.getName() + ", Descripción: " + task.getDescription());
        }
    }

    private void viewCompletedTasks() {
        List<Task> tasks = taskService.getCompletedTasks();
        System.out.println("Tareas Completadas:");
        for (Task task : tasks) {
            System.out.println("ID: " + task.getId() + ", Nombre: " + task.getName() + ", Descripción: " + task.getDescription());
        }
    }
}

