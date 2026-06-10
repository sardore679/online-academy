package online_academy.service;

import lombok.RequiredArgsConstructor;
import online_academy.dto.auth_dto.AuthRequestDto;
import online_academy.dto.auth_dto.AuthResponseDto;
import online_academy.dto.user_dto.UserRequestDto;
import online_academy.entity.User;
import online_academy.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponseDto register(UserRequestDto dto) {

        if (repository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Bu email allaqachon ro'yhatdan o'tgan!");
        }

        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setFullName(dto.fullName());
        user.setRole(dto.role());

        repository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }

    public AuthResponseDto login(AuthRequestDto dto) {

        User user = repository.findByEmail(dto.email())
                .orElseThrow( () -> new RuntimeException("Email yoki parol noto'g'ri!"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Email yoki parol noto'g'ri!");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }

}
