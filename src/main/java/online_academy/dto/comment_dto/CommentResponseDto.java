package online_academy.dto.comment_dto;

import java.time.LocalDateTime;

public record CommentResponseDto(

        Long id,
        String content,
        Long lessonId,
        String lessonTitle,
        Long userId,
        String username,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {}
