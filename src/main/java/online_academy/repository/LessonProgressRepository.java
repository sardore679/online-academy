package online_academy.repository;


import online_academy.entity.Lesson;
import online_academy.entity.LessonProgress;
import online_academy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {

    List<LessonProgress> findByStudent(User student);

    Optional<LessonProgress> findByStudentAndLesson(User student, Lesson lesson);

    boolean existsByStudentAndLesson(User student, Lesson lesson);

    List<LessonProgress> findByStudentAndLessonCourse(User student, online_academy.entity.Course course);

}
