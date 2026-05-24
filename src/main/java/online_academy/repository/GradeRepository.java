package online_academy.repository;

import online_academy.entity.Grade;
import online_academy.entity.Lesson;
import online_academy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findByStudent(User student);

    List<Grade> findByLesson(Lesson lesson);

    Optional<Grade> findByStudentAndLesson(User student, Lesson lesson);

    boolean existsByStudentAndLesson(User student, Lesson lesson);

}
