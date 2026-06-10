package online_academy.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online_academy.dto.course_dto.CourseRequestDto;
import online_academy.dto.course_dto.CourseResponseDto;
import online_academy.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourse() {
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(@Valid @RequestBody CourseRequestDto dto) {
        return ResponseEntity.ok(courseService.createCourse(dto));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseResponseDto>> searchCourse(@RequestParam String title) {
        return ResponseEntity.ok(courseService.searchCourses(title));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }




}
