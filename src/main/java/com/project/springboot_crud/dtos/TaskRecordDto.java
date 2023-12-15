package com.project.springboot_crud.dtos;

import jakarta.validation.constraints.NotBlank;

public record TaskRecordDto(@NotBlank String name, @NotBlank String description) {
}
