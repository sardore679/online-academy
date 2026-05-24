package online_academy.dto.lesson_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LessonRequestDto(

        @NotBlank(message = "Dars nomi bo'sh bo'lmasligi kerak")
        String title,

        @NotBlank(message = "Dars matni bo'sh bo'lmasligi kerak")
        String content,

        @NotNull(message = "Tartib raqam bo'sh bo'lmasligi kerak")
        Integer orderNumber,

        @NotNull(message = "Kurs id bo'sh bo'lmasligi kerak")
        Long courseId

) {}
