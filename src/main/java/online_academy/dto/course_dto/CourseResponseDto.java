package online_academy.dto.course_dto;

import java.time.LocalDateTime;

public record CourseResponseDto(
        Long id,
        String title,
        String description,
        String teacherName,
        LocalDateTime createdAt
) {}
