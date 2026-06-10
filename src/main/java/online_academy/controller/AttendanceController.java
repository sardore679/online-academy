package online_academy.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online_academy.dto.attendance_dto.AttendanceRequestDto;
import online_academy.dto.attendance_dto.AttendanceResponseDto;
import online_academy.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public ResponseEntity<AttendanceResponseDto> markAttendance(@Valid @RequestBody AttendanceRequestDto dto) {
        return ResponseEntity.ok(attendanceService.markAttendance(dto));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponseDto>> getStudentAttendance(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getStudentAttendance(studentId));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<AttendanceResponseDto>> getLessonAttendance(@PathVariable Long lessonId) {
        return ResponseEntity.ok(attendanceService.getLessonAttendance(lessonId));
    }

    @GetMapping("/my")
    public ResponseEntity<List<AttendanceResponseDto>> getMyAttendance() {
        return ResponseEntity.ok(attendanceService.getMyAttendance());
    }


}
