package online_academy.dto.user_dto;

import online_academy.entity.enems.Role;

public record UserResponseDto(
    Long id,
    String email,
    String fullName,
    Role role
) {}
