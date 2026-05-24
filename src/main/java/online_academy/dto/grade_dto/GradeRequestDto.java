package online_academy.dto.grade_dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record GradeRequestDto(

        @NotNull(message = "Student id bo'sh bo'lmasligi kerak")
        Long studentId,

        @NotNull(message = "Dars id bo'sh bo'lmasligi kerak")
        Long lessonId,

        @NotNull(message = "Baho bo'sh bo'lmasligi kerak")
        @Min(value = 0, message = "Baho 0 dan kam bo'lmasligi kerak")
        @Max(value = 100, message = "Baho 100 dan ko'p bolmasligi kerak")
        Integer score,

        String comment

) {}
