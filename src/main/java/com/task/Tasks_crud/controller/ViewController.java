package com.task.Tasks_crud.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.task.Tasks_crud.dto.TaskRequest;
import com.task.Tasks_crud.dto.TaskResponse;
import com.task.Tasks_crud.entity.TaskEntity;
import com.task.Tasks_crud.service.TaskService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final TaskService taskService;

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String state,
            Model model) {

        if (state != null && !state.isBlank()) {

            model.addAttribute(
                    "tasks",
                    taskService.getTasksByState(state));

        } else {

            model.addAttribute(
                    "tasks",
                    taskService.getAllTask());
        }

        return "index";
    }

    @GetMapping("/tasks/new")
    public String newTask(Model model) {

        model.addAttribute("task", new TaskRequest());

        return "new-task";
    }

    @PostMapping("/tasks/create")
    public String createTask(TaskRequest request) {

        request.setDateCreation(
                new Date(System.currentTimeMillis()));

        taskService.createTask(request);

        return "redirect:/";
    }

    @GetMapping("/tasks/edit/{id}")
    public String editTask(
            @PathVariable Long id,
            Model model) {

        TaskEntity task = taskService.getTaskById(id);

        model.addAttribute("task", task);

        return "edit-task";
    }

    @PostMapping("/tasks/update/{id}")
    public String updateTask(
            @PathVariable Long id,
            TaskRequest request) {

        taskService.updateTask(id, request);

        return "redirect:/";
    }

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(
            @PathVariable Long id) {

        taskService.deleteTask(id);

        return "redirect:/";
    }
}