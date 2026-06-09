package online_academy.dto.quiz_dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import online_academy.entity.enems.QuizStatus;

public record QuizRequestDto(
        @NotBlank(message = "Quiz nomi bo'sh bo'lmasligi kerak")
        String title,

        @NotNull(message = "Vaqt bo'sh bo'lmasligi kerak")
        @Min(value = 1, message = "Vaqt kamida 1 daqiqa bo'lishi kerak")
        Integer durationMinutes,

        @NotNull(message = "O'tish bali bo'sh bo'lmasligi kerak")
        @Min(value = 1, message = "O'tish bali kamida 1 bo'lishi kerak")
        Integer passingScore,

        @NotNull(message = "Status bo'sh bo'lmasligi kerak")
        QuizStatus status,

        @NotNull(message = "Dars id bo'sh bo'sh bo'lmasligi kerak")
        Long lessonId

) {}
