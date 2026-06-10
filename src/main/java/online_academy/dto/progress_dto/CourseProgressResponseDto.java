package online_academy.dto.progress_dto;

public record CourseProgressResponseDto(

        Long courseId,
        String courseTitle,
        int totalLessons,
        int completedLesson,
        double progressPercent

) {}
