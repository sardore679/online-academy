package online_academy.service;


import lombok.RequiredArgsConstructor;
import online_academy.dto.dashboard_dto.DashboardResponseDto;
import online_academy.entity.enems.Role;
import online_academy.repository.*;
import online_academy.repository.quiz_repo.QuizRepository;
import online_academy.repository.quiz_repo.QuizResultRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final GroupRepository groupRepository;
    private final QuizRepository quizRepository;
    private final QuizResultRepository quizResultRepository;
    private final AttendanceRepository attendanceRepository;

    public DashboardResponseDto getDashboard() {
        return new DashboardResponseDto(
                userRepository.count(),

                userRepository.countByRole(Role.STUDENT),
                userRepository.countByRole(Role.TEACHER),
                userRepository.countByRole(Role.ADMIN),

                courseRepository.count(),
                lessonRepository.count(),

                enrollmentRepository.count(),
                groupRepository.count(),
                quizRepository.count(),
                quizResultRepository.count(),
                attendanceRepository.count()
        );
    }

}
