package online_academy.dto.group_dto;

import java.util.List;

public record GroupResponseDto(

        Long id,
        String name,
        String teacherName,
        String courseName,
        List<String> studentNames

) {}
