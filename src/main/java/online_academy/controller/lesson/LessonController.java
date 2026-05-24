package online_academy.controller.lesson;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online_academy.dto.lesson_dto.LessonRequestDto;
import online_academy.dto.lesson_dto.LessonResponseDto;
import online_academy.service.lesson_service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;


    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<LessonResponseDto>> getLessonByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(lessonService.getLessonsByCourse(courseId));
    }

    @GetMapping("/id")
    public ResponseEntity<LessonResponseDto> getLessonById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public ResponseEntity<LessonResponseDto> createLesson(@Valid @RequestBody LessonRequestDto dto) {
        return ResponseEntity.ok(lessonService.createLesson(dto));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }

}
