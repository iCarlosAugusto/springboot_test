package com.project.springboot_crud.controllers;

import com.project.springboot_crud.dtos.TaskRecordDto;
import com.project.springboot_crud.dtos.UpdateTaskDto;
import com.project.springboot_crud.models.TaskModel;
import com.project.springboot_crud.repositories.TaskRepository;
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
    TaskRepository taskRepository;

    @PostMapping("/tasks")
    ResponseEntity<TaskModel> createTask(@RequestBody @Valid TaskRecordDto taskRecordDto) {
        var taskModel = new TaskModel();
        BeanUtils.copyProperties(taskRecordDto, taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(taskModel));
    }

    @GetMapping("/tasks")
    ResponseEntity<List<TaskModel>> listTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.findAll());
    }

    @DeleteMapping("/tasks/{id}")
    ResponseEntity<Object> deleteTask(@PathVariable(value = "id") UUID id){
        Optional<TaskModel> task = taskRepository.findById(id);
        if(task.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
        taskRepository.delete(task.get());
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted");
    }


    @PutMapping("/tasks/{id}")
    ResponseEntity<Object> updateTask(
            @PathVariable(value = "id") UUID id,
            @RequestBody UpdateTaskDto updateTaskDto
    ) {
        Optional<TaskModel> task = taskRepository.findById(id);
        if(task.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
        var taskUpdated = task.get();
        taskUpdated.setStatus(updateTaskDto.status());
        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.save(taskUpdated));
    }

}
