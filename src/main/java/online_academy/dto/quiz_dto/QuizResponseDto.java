package online_academy.dto.quiz_dto;

import online_academy.entity.enems.QuizStatus;

public record QuizResponseDto(

        Long id,
        String title,
        Integer durationMinutes,
        Integer passingScore,
        QuizStatus status,
        String lessonTitle

) {}
