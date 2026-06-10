package online_academy.service;


import lombok.RequiredArgsConstructor;
import online_academy.dto.attendance_dto.AttendanceRequestDto;
import online_academy.dto.attendance_dto.AttendanceResponseDto;
import online_academy.entity.Attendance;
import online_academy.entity.Lesson;
import online_academy.entity.User;
import online_academy.repository.AttendanceRepository;
import online_academy.repository.LessonRepository;
import online_academy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    private AttendanceResponseDto toDto(Attendance attendance) {
        return new AttendanceResponseDto(
                attendance.getId(),
                attendance.getStudent().getFullName(),
                attendance.getLesson().getTitle(),
                attendance.getPresent(),
                attendance.getDate()
        );
    }

    public AttendanceResponseDto markAttendance(AttendanceRequestDto dto) {
        User student = userRepository.findById(dto.studentId())
                .orElseThrow(() -> new RuntimeException("Student topilmadi: " + dto.studentId()));

        Lesson lesson = lessonRepository.findById(dto.lessonId())
                .orElseThrow(() -> new RuntimeException("Dars topilmadi: " + dto.lessonId()));

        if (attendanceRepository.existsByStudentAndLesson(student, lesson)) {
            throw new RuntimeException("Bu student bu darsda davomat allaqachon belgilangan!");
        }

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setLesson(lesson);
        attendance.setPresent(dto.present());
        attendance.setDate(dto.date());

        Attendance saved = attendanceRepository.save(attendance);
        return toDto(saved);
    }

    public List<AttendanceResponseDto> getStudentAttendance(Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student topilmadi: " + studentId));

        return attendanceRepository.findByStudent(student)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<AttendanceResponseDto> getLessonAttendance(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Dars topilmadi: " + lessonId));

        return attendanceRepository.findByLesson(lesson)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<AttendanceResponseDto> getMyAttendance() {
        String email = org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getName();
        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi: "));

        return attendanceRepository.findByStudent(student)
                .stream()
                .map(this::toDto)
                .toList();
    }

}
