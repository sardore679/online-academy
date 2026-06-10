package online_academy.service;


import lombok.RequiredArgsConstructor;
import online_academy.dto.lesson_dto.LessonRequestDto;
import online_academy.dto.lesson_dto.LessonResponseDto;
import online_academy.entity.Course;
import online_academy.entity.Lesson;
import online_academy.repository.CourseRepository;
import online_academy.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    private LessonResponseDto toDto(Lesson lesson) {
        return new LessonResponseDto(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getContent(),
                lesson.getOrderNumber(),
                lesson.getCourse().getTitle()
        );
    }

    public List<LessonResponseDto> getLessonsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow( () -> new RuntimeException("Kurs topilmadi: " + courseId));
        return lessonRepository.findByCourseOrderByOrderNumberAsc(course)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public LessonResponseDto getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Dars topilmadi: " + id));
        return toDto(lesson);
    }

    public LessonResponseDto createLesson(LessonRequestDto dto) {
        Course course = courseRepository.findById(dto.courseId())
                .orElseThrow( ()-> new RuntimeException("Kurs topilmadi: " + dto.courseId()));

        Lesson lesson = new Lesson();
        lesson.setTitle(dto.title());
        lesson.setContent(dto.content());
        lesson.setOrderNumber(dto.orderNumber());
        lesson.setCourse(course);

        Lesson saved = lessonRepository.save(lesson);
        return toDto(saved);
    }

    public void deleteLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Dars topilmadi: " + id));
        lessonRepository.delete(lesson);
    }


}
