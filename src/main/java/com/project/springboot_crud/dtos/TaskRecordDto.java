package com.project.springboot_crud.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record TaskRecordDto(
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String urlImage

    ) {
}
