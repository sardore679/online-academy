package online_academy.dto.quiz_dto;

import java.util.List;

public record QuestionResponseDto(

        Long id,
        String text,
        List<OptionResponseDto> options

) {}
