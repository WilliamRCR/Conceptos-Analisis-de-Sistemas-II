package Application;

import Data.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class TaskService implements ITaskService {
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;
    private final Lock lock = new ReentrantLock();

    @Override
    public void updateTask(Task task) {
        lock.lock();
        try {
            Task existingTask = tasks.stream()
                    .filter(t -> t.getId() == task.getId())
                    .findFirst()
                    .orElse(null);
            if (existingTask != null) {
                existingTask.setName(task.getName());
                existingTask.setDescription(task.getDescription());
                existingTask.setCompleted(task.isCompleted());
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void addTask(Task task) {

    }

    @Override
    public void deleteTask(int taskId) {
        lock.lock();
        try {
            tasks.removeIf(t -> t.getId() == taskId);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<Task> getPendingTasks() {
        lock.lock();
        try {
            return tasks.stream()
                    .filter(t -> !t.isCompleted())
                    .collect(Collectors.toList());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<Task> getCompletedTasks() {
        lock.lock();
        try {
            return tasks.stream()
                    .filter(Task::isCompleted)
                    .collect(Collectors.toList());
        } finally {
            lock.unlock();
        }
    }
}

