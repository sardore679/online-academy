package online_academy.service;


import lombok.RequiredArgsConstructor;
import online_academy.dto.progress_dto.CourseProgressResponseDto;
import online_academy.dto.progress_dto.LessonProgressResponseDto;
import online_academy.entity.Course;
import online_academy.entity.Lesson;
import online_academy.entity.LessonProgress;
import online_academy.entity.User;
import online_academy.repository.CourseRepository;
import online_academy.repository.LessonProgressRepository;
import online_academy.repository.LessonRepository;
import online_academy.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonProgressService {

    private final LessonProgressRepository lessonProgressRepository;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi!"));
    }

    private LessonProgressResponseDto toDto(LessonProgress progress) {
        return new LessonProgressResponseDto(
                progress.getId(),
                progress.getLesson().getTitle(),
                progress.getCompleted(),
                progress.getCompletedAt()
        );
    }

    public LessonProgressResponseDto completeLesson(Long lessonId) {
        User student = getCurrentUser();

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Dars topilmadi: " + lessonId));

        if (lessonProgressRepository.existsByStudentAndLesson(student, lesson)) {
            throw new RuntimeException("Siz bu darsni allaqachon tugatgansiz!");
        }

        LessonProgress progress = new LessonProgress();
        progress.setStudent(student);
        progress.setLesson(lesson);
        progress.setCompleted(true);

        return toDto(lessonProgressRepository.save(progress));
    }

    public CourseProgressResponseDto getCourseProgress(Long courseId) {
        User student = getCurrentUser();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Kurs topilmadi: " + courseId));

        List<Lesson> allLessons = lessonRepository.findByCourseOrderByOrderNumberAsc(course);
        int totalLesson = allLessons.size();

        List<LessonProgress> completedProgresses = lessonProgressRepository
                .findByStudentAndLessonCourse(student, course);
        int completedLessons = completedProgresses.size();

        double progressPercent = totalLesson == 0 ? 0 :
                Math.round((completedLessons * 100.0 / totalLesson) * 10.0) / 10.0;

        return new CourseProgressResponseDto(
                course.getId(),
                course.getTitle(),
                totalLesson,
                completedLessons,
                progressPercent
        );
    }

    public List<LessonProgressResponseDto> getMyProgress() {
        User student = getCurrentUser();
        return lessonProgressRepository.findByStudent(student)
                .stream()
                .map(this::toDto)
                .toList();
    }

}
