package com.task.Tasks_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.task.Tasks_crud.service.TaskService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final TaskService taskService;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
            "tasks",
            taskService.getAllTask()
        );

        return "index";
    }
}