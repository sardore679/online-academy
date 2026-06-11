package online_academy.dto.comment_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentRequestDto (

        @NotNull(message = "Lesson ID bo'lishi shart")
        Long lessonId,

        @NotBlank(message = "Comment bo'sh bo'lmasin")
        @Size(min = 2, max = 500, message = "Comment 2 dan 500 belgigacha bo'lsin")
        String content

) {}
