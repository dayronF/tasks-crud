package com.task.Tasks_crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.task.Tasks_crud.dto.TaskMessage;
import com.task.Tasks_crud.dto.TaskRequest;
import com.task.Tasks_crud.dto.TaskResponse;
import com.task.Tasks_crud.entity.TaskEntity;
import com.task.Tasks_crud.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskMessage createTask(TaskRequest request) {

        TaskMessage message = new TaskMessage();

        TaskEntity task = new TaskEntity();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setState(request.getState());
        task.setDateCreation(request.getDateCreation());
        taskRepository.save(task);

        message.setMessage("Tarea registrada correctmente");

        return message;
    }

    public TaskEntity getTaskById(Long id) {

        return taskRepository.findById(id).orElse(null);

    }

    public TaskMessage updateTask(Long id, TaskRequest request) {

        TaskMessage message = new TaskMessage();

        TaskEntity task = taskRepository.findById(id).orElse(null);

        if (task == null) {

            message.setMessage("Tarea no encontrada");
            return message;

        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setState(request.getState());

        taskRepository.save(task);

        message.setMessage("Tarea actualizada correctamente");

        return message;
    }

    public TaskMessage deleteTask(Long id) {

        TaskMessage message = new TaskMessage();

        Optional<TaskEntity> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            message.setMessage("Tarea no encontrada");
            return message;
        }
;
        taskRepository.deleteById(id);
        message.setMessage("Tarea eliminada correctamente");
        return message;
    }

    public List<TaskResponse> getAllTask() {

        List<TaskEntity> tasks = taskRepository.findAll();
        List<TaskResponse> responses = new ArrayList<>();

        for (TaskEntity task : tasks) {

            TaskResponse response = new TaskResponse();

            response.setId(task.getId());
            response.setTitle(task.getTitle());
            response.setDescription(task.getDescription());
            response.setDateCreation(task.getDateCreation());
            response.setState(task.getState());

            responses.add(response);
        }
     
        return responses;

    }

    public List<TaskResponse> getTasksByState(String state) {

    List<TaskEntity> tasks =
            taskRepository.findByState(state);

    List<TaskResponse> responses =
            new ArrayList<>();

    for (TaskEntity task : tasks) {

        TaskResponse response =
                new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setState(task.getState());
        response.setDateCreation(task.getDateCreation());

        responses.add(response);
    }

    return responses;
}

}
