package online_academy.service.course_service;


import lombok.RequiredArgsConstructor;
import online_academy.dto.course_dto.CourseRequestDto;
import online_academy.dto.course_dto.CourseResponseDto;
import online_academy.entity.Course;
import online_academy.entity.User;
import online_academy.repository.CourseRepository;
import online_academy.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    private CourseResponseDto toDto(Course course) {
        return new CourseResponseDto(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getTeacher().getFullName(),
                course.getCreatedAt()
        );
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return userRepository.findByEmail(email)
                .orElseThrow( () -> new RuntimeException("Foydalanuvchi topilmadi!"));
    }

    public List<CourseResponseDto> getAllCourse() {
        return courseRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public CourseResponseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Kurs topilmadi: " + id));
        return toDto(course);
    }

    public CourseResponseDto createCourse(CourseRequestDto dto) {
        User teacher = getCurrentUser();

        Course course = new Course();
        course.setTitle(dto.title());
        course.setDescription(dto.description());
        course.setTeacher(teacher);

        Course saved = courseRepository.save(course);
        return toDto(saved);

    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Kurs topilmadi: " + id));
        User currentUser = getCurrentUser();

        if (!course.getTeacher().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Siz bu kursni o'chira olmaysiz");
        }

        courseRepository.delete(course);
    }

}
