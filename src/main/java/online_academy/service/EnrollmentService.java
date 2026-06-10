package online_academy.service;


import lombok.RequiredArgsConstructor;
import online_academy.dto.enrollment_dto.EnrollmentRequestDto;
import online_academy.dto.enrollment_dto.EnrollmentResponseDto;
import online_academy.entity.Course;
import online_academy.entity.Enrollment;
import online_academy.entity.User;
import online_academy.repository.CourseRepository;
import online_academy.repository.EnrollmentRepository;
import online_academy.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    private EnrollmentResponseDto toDto(Enrollment enrollment) {
        return new EnrollmentResponseDto(
                enrollment.getId(),
                enrollment.getStudent().getFullName(),
                enrollment.getCourse().getTitle(),
                enrollment.getEnrolledAT()
        );
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi!"));
    }

    public EnrollmentResponseDto enroll(EnrollmentRequestDto dto) {
        User student = getCurrentUser();

        Course course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new RuntimeException("Kurs topilmadi!"));

        if (enrollmentRepository.existsByStudentAndCourse(student, course)) {
            throw new RuntimeException("Siz bu kursga allaqachon yozilgansiz");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        Enrollment saved = enrollmentRepository.save(enrollment);
        return toDto(saved);
    }

    public List<EnrollmentResponseDto> getMyEnrollments() {
        User student = getCurrentUser();
        return enrollmentRepository.findByStudent(student)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<EnrollmentResponseDto> getCourseEnrollments(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Kurs topilmadi"));
        return enrollmentRepository.findByCourse(course)
                .stream()
                .map(this::toDto)
                .toList();
    }



}
