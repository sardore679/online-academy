package online_academy.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online_academy.dto.grade_dto.GradeRequestDto;
import online_academy.dto.grade_dto.GradeResponseDto;
import online_academy.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PreAuthorize("hsdRole('TEACHER')")
    @PostMapping
    public ResponseEntity<GradeResponseDto> addGrade(@Valid @RequestBody GradeRequestDto dto) {
        return ResponseEntity.ok(gradeService.addGrade(dto));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeResponseDto>> getStudentGrades(@PathVariable Long studentId) {
        return ResponseEntity.ok(gradeService.getStudentGrades(studentId));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<GradeResponseDto>> getLessonGrades(@PathVariable Long lessonId) {
        return ResponseEntity.ok(gradeService.getLessonGrades(lessonId));
    }

    @GetMapping("/my")
    public ResponseEntity<List<GradeResponseDto>> getMyGrades() {
        return ResponseEntity.ok(gradeService.getMyGrades());
    }

}
