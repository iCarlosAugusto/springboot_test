package com.project.springboot_crud.services;

import com.project.springboot_crud.dtos.TaskRecordDto;
import com.project.springboot_crud.dtos.UpdateTaskDto;
import com.project.springboot_crud.models.TaskModel;
import com.project.springboot_crud.repositories.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public ResponseEntity<TaskModel> create(TaskRecordDto taskRecordDto){
        var taskModel = new TaskModel();
        BeanUtils.copyProperties(taskRecordDto, taskModel);
        taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskModel);
    }

    public ResponseEntity<List<TaskModel>> list() {
        List<TaskModel> tasks = taskRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    public Optional<TaskModel> findById(UUID id) {
        return taskRepository.findById(id);
    }

    public ResponseEntity<Object> delete(UUID id) {
        Optional<TaskModel> task = this.findById(id);
        if(task.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
        taskRepository.delete(task.get());
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted");
    }

    public ResponseEntity<Object> update(UUID id, UpdateTaskDto updateTaskDto) {
        Optional<TaskModel> task = taskRepository.findById(id);
        if(task.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
        var taskUpdated = task.get();
        taskUpdated.setStatus(updateTaskDto.status());
        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.save(taskUpdated));
    }
}
