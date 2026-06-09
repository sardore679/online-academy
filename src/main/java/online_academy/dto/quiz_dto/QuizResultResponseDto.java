package online_academy.dto.quiz_dto;

import java.time.LocalDateTime;

public record QuizResultResponseDto(

        Long id,
        String studentName,
        String quizTitle,
        Integer score,
        Integer totalQuestions,
        Boolean passed,
        LocalDateTime submittedAt

) {}
