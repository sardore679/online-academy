package online_academy.dto.lesson_dto;

public record LessonResponseDto(

       Long id,
       String title,
       String content,
       Integer orderNumber,
       String courseTitle

) {}
