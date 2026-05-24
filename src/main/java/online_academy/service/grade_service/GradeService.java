package online_academy.service.grade_service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online_academy.dto.grade_dto.GradeRequestDto;
import online_academy.dto.grade_dto.GradeResponseDto;
import online_academy.entity.Grade;
import online_academy.entity.Lesson;
import online_academy.entity.User;
import online_academy.repository.GradeRepository;
import online_academy.repository.LessonRepository;
import online_academy.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    private GradeResponseDto toDto(Grade grade) {
        return new GradeResponseDto(
                grade.getId(),
                grade.getStudent().getFullName(),
                grade.getLesson().getTitle(),
                grade.getScore(),
                grade.getComment()
        );
    }

    public GradeResponseDto addGrade(GradeRequestDto dto) {
        User student = userRepository.findById(dto.studentId())
                .orElseThrow(() -> new RuntimeException("Student topilmadi: " + dto.studentId()));

        Lesson lesson = lessonRepository.findById(dto.lessonId())
                .orElseThrow(() -> new RuntimeException("Dars topilmadi: " + dto.lessonId()));

        if (gradeRepository.existsByStudentAndLesson(student, lesson)) {
            throw new RuntimeException("Bu student bu darsda allaqachon baho olgan!");
        }

        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setLesson(lesson);
        grade.setScore(dto.score());
        grade.setComment(dto.comment());

        Grade saved = gradeRepository.save(grade);
        return toDto(saved);

    }

    public List<GradeResponseDto> getStudentGrades(Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student topilmadi: " + studentId));
        return gradeRepository.findByStudent(student)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<GradeResponseDto> getLessonGrades(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Dars topilmadi: " + lessonId));

        return gradeRepository.findByLesson(lesson)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<GradeResponseDto> getMyGrades() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi!"));
        return gradeRepository.findByStudent(student)
                .stream()
                .map(this::toDto)
                .toList();
    }



}
