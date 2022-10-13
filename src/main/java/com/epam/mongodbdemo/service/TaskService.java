package com.epam.mongodbdemo.service;

import com.epam.mongodbdemo.model.Task;
import com.epam.mongodbdemo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task addTask(Task task) {
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }

    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }

    public Task getByTaskId(String taskId) {
        return taskRepository.findById(taskId).get();
    }

    public List<Task> getTaskBySeverity(int severity) {
        return taskRepository.findBySeverity(severity);
    }

    public List<Task> getTaskByAssignee(String assignee) {
        return taskRepository.getTaskByAssignee(assignee);
    }

    public Task updateTask(Task task) {
        Task taskRequest = taskRepository.findById(task.getTaskId()).get();
        taskRequest.setDescription(task.getDescription());
        taskRequest.setAssignee(task.getAssignee());
        taskRequest.setSeverity(task.getSeverity());
        taskRequest.setStoryPoint(task.getStoryPoint());
        return taskRepository.save(taskRequest);
    }

    public String deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
        return "Task deleted" + taskId;
    }
}
