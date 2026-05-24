package online_academy.service.user_service;

import lombok.RequiredArgsConstructor;
import online_academy.dto.user_dto.UserRequestDto;
import online_academy.dto.user_dto.UserResponseDto;
import online_academy.entity.User;
import online_academy.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    private User toEntity(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setFullName(dto.fullName());
        user.setRole(dto.role());
        return user;
    }

    private UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getRole()
        );
    }

    public List<UserResponseDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public UserResponseDto getUserById(Long id) {
        User user  = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User topilmadi: " + id));
        return toDto(user);
    }

    public UserResponseDto saveUser(UserRequestDto dto) {
        User user = toEntity(dto);
        User saved = repository.save(user);
        return toDto(saved);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
