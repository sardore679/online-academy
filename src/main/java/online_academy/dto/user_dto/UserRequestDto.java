package online_academy.dto.user_dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import online_academy.entity.enems.Role;

public record UserRequestDto(

        @NotBlank(message = "Email bosh bo'lmasligi kerak")
        @Email(message = "Email farmati noto'g'ri")
        String email,

        @NotBlank(message = "Parol bosh bolmasligi kerak")
        @Size(min = 8, message = "Parol kamida 8 ta belgi bo'lishi kerak")
        String password,

        @NotBlank(message = "Ism bosh bolmasligi kerak")
        String fullName,

        @jakarta.validation.constraints.NotNull(message = "Rol bo'sh bo'lmasligi kerak")
        Role role
) {}
