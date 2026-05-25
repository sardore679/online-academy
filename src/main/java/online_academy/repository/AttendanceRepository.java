package online_academy.repository;

import online_academy.entity.Attendance;
import online_academy.entity.Lesson;
import online_academy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudent(User student);

    List<Attendance> findByLesson(Lesson lesson);

    Optional<Attendance> findByStudentAndLesson(User student, Lesson lesson);

    boolean existsByStudentAndLesson(User student, Lesson lesson);

}
