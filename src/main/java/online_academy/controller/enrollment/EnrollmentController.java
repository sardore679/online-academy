package online_academy.controller.enrollment;


import lombok.RequiredArgsConstructor;
import online_academy.dto.enrollment_dto.EnrollmentRequestDto;
import online_academy.dto.enrollment_dto.EnrollmentResponseDto;
import online_academy.service.enrollment_service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponseDto> enroll(@RequestBody EnrollmentRequestDto dto) {
        return ResponseEntity.ok(enrollmentService.enroll(dto));
    }

    @GetMapping("/my")
    public ResponseEntity<List<EnrollmentResponseDto>> getMyEnrollments() {
        return ResponseEntity.ok(enrollmentService.getMyEnrollments());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDto>> getCourseEnrollments(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getCourseEnrollments(courseId));
    }

}
