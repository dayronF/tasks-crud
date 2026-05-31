package com.task.Tasks_crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.Tasks_crud.dto.TaskMessage;
import com.task.Tasks_crud.dto.TaskRequest;
import com.task.Tasks_crud.dto.TaskResponse;
import com.task.Tasks_crud.entity.TaskEntity;
import com.task.Tasks_crud.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskMessage> createTask(@RequestBody TaskRequest request) {
        TaskMessage message = taskService.createTask(request);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Long id) {
        TaskEntity task = taskService.getTaskById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskMessage> updateTask(@PathVariable Long id, @RequestBody TaskRequest request) {
        TaskMessage message = taskService.updateTask(id, request);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskMessage> deleteTask(@PathVariable Long id) {
        TaskMessage message = taskService.deleteTask(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> tasks = taskService.getAllTask();
        return ResponseEntity.ok(tasks);
    }
}