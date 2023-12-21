package com.project.springboot_crud.controllers;

import com.project.springboot_crud.dtos.TaskRecordDto;
import com.project.springboot_crud.dtos.UpdateTaskDto;
import com.project.springboot_crud.models.TaskModel;
import com.project.springboot_crud.repositories.TaskRepository;
import com.project.springboot_crud.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/tasks")
    ResponseEntity<TaskModel> createTask(@RequestBody @Valid TaskRecordDto taskRecordDto) {
        return taskService.create(taskRecordDto);
    }

    @GetMapping("/tasks")
    ResponseEntity<List<TaskModel>> listTasks() {
        return taskService.list();
    }

    @DeleteMapping("/tasks/{id}")
    ResponseEntity<Object> deleteTask(@PathVariable(value = "id") UUID id){
      return taskService.delete(id);
    }


    @PutMapping("/tasks/{id}")
    ResponseEntity<Object> updateTask(
            @PathVariable(value = "id") UUID id,
            @RequestBody UpdateTaskDto updateTaskDto
    ) {
        return taskService.update(id, updateTaskDto);
    }

}
