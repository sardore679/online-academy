package online_academy.dto.quiz_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import online_academy.entity.Option;

import java.util.List;
import java.util.Optional;

public record QuestionRequestDto(

        @NotBlank(message = "Savol matni bo'sh bo'lmasligi kerak")
        String text,

        @NotNull(message = "Quiz id bo'sh bo'lmasligi kerak")
        Long quizId,

        @Size(min = 2, max = 4, message = "Kamida 2 ta, ko'pi bilan 4 ta variant bo'lishi kerak")
        List<OptionRequestDto> options

) {}
