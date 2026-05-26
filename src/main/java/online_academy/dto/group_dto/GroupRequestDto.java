package online_academy.dto.group_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GroupRequestDto(

        @NotBlank(message = "Guruh nomi bo'sh bo'lmasligi kerak")
        String name,

        @NotNull(message = "Teacher id bo'sh bo'lmasligi kerak")
        Long teacherId,

        @NotNull(message = "Kurs id bo'sh bo'lmasligi kerak")
        Long courseId

) {}
