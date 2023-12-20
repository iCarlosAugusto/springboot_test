package com.project.springboot_crud.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record UpdateTaskDto(
        @NotBlank Integer status

) {

}
