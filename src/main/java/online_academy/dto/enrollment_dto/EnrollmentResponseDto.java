package online_academy.dto.enrollment_dto;

import java.time.LocalDateTime;

public record EnrollmentResponseDto(
        Long id,
        String studentName,
        String courseTitle,
        LocalDateTime enrolledAt
) {}
