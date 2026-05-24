package online_academy.dto.grade_dto;

public record GradeResponseDto(

        Long id,
        String studentId,
        String lessonTitle,
        Integer score,
        String comment

) {}
