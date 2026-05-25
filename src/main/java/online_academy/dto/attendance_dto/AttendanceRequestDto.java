package online_academy.dto.attendance_dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AttendanceRequestDto(

        @NotNull(message = "Student id bo'sh bolmasligi kerak")
        Long studentId,

        @NotNull(message = "Dars id bo'sh bo'lmasligi kerak")
        Long lessonId,

        @NotNull(message = "Davomat holati bo'sh bo'lmasligi kerak")
        Boolean present,

        @NotNull(message = "Sana bo'sh bo'lmasligi kerak")
        LocalDate date

) {}
