package online_academy.dto.attendance_dto;

import java.time.LocalDate;

public record AttendanceResponseDto(

        Long id,
        String studentName,
        String lessonTitle,
        Boolean present,
        LocalDate date

) {}
