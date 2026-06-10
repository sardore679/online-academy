package online_academy.controller;


import lombok.RequiredArgsConstructor;
import online_academy.dto.progress_dto.CourseProgressResponseDto;
import online_academy.dto.progress_dto.LessonProgressResponseDto;
import online_academy.service.LessonProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class LessonProgressController {

    private final LessonProgressService lessonProgressService;


    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/complete/{lessonId}")
    public ResponseEntity<LessonProgressResponseDto> completeLesson(@PathVariable Long lessonId) {
        return ResponseEntity.ok(lessonProgressService.completeLesson(lessonId));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/course/{courseId}")
    public ResponseEntity<CourseProgressResponseDto> getCourseProgress(@PathVariable Long courseId) {
        return ResponseEntity.ok(lessonProgressService.getCourseProgress(courseId));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/my")
    public ResponseEntity<List<LessonProgressResponseDto>> getMyProgress() {
        return ResponseEntity.ok(lessonProgressService.getMyProgress());
    }

}
