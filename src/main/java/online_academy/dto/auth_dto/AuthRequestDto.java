package online_academy.dto.auth_dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequestDto(

       @NotBlank(message = "Email bo'sh bo'lmasligi kerak")
       @Email(message = "Email formati noto'g'ri")
       String email,

       @NotBlank(message = "Parol bo'sh bo'lmasligi kerak")
       String password
) {}
