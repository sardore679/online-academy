package online_academy.dto.dashboard_dto;

public record DashboardResponseDto(

        Long totalUsers,
        Long totalStudents,
        Long totalTeachers,
        Long totalAdmins,

        Long totalCourse,
        Long totalLesson,

        Long totalEnrollments,
        Long totalGroups,
        Long totalQuizzes,
        Long totalQuizResults,
        Long totalAttendances

) {}
