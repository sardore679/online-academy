package online_academy.dto.error_dto;

public record ErrorResponseDto(
        String message,
        int status
) {}
