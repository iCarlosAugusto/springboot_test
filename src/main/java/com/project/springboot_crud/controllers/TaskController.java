package com.project.springboot_crud.controllers;

import com.project.springboot_crud.dtos.TaskRecordDto;
import com.project.springboot_crud.models.TaskModel;
import com.project.springboot_crud.repositories.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
