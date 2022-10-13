package com.epam.mongodbdemo.controller;

import com.epam.mongodbdemo.model.Task;
import com.epam.mongodbdemo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.findAllTask();
    }

    @GetMapping("/{taskId}")
    public Task getTaskByTaskId(@PathVariable String taskId) {
        return taskService.getByTaskId(taskId);
    }

    @GetMapping("/severity/{severity}")
    public List<Task> getTaskBySeverity(@PathVariable int severity) {
        return taskService.getTaskBySeverity(severity);
    }

    @GetMapping("/assignee/{assignee}")
    public List<Task> getByTaskAssignee(@PathVariable String assignee) {
        return taskService.getTaskByAssignee(assignee);
    }

    @PutMapping
    public Task update(@RequestBody Task task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping("{taskId}")
    public String delete(@PathVariable String taskId) {
        return taskService.deleteTask(taskId);
    }
}
