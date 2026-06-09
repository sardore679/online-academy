package online_academy.dto.quiz_dto;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record QuizSubmitDto(

        @NotNull(message = "Javoblar bo'sh bo'lmasligi kerak")
        Map<Long, Long> answers

) {}
