package online_academy.dto.quiz_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.swing.text.StyledEditorKit;

public record OptionRequestDto(

        @NotBlank(message = "Variant matni bo'sh bo'lmasligi kerak")
        String text,

        @NotNull(message = "isCorrect bo'sh bo'lmasligi kerak")
        Boolean isCorrect

) {}
