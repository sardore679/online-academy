package online_academy.dto.progress_dto;

import java.time.LocalDateTime;

public record LessonProgressResponseDto(

        Long id,
        String lessonTitle,
        Boolean completed,
        LocalDateTime completedAt

) {}
