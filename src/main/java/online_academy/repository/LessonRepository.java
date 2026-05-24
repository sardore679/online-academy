package online_academy.repository;

import online_academy.entity.Course;
import online_academy.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByCourseOrderByOrderNumberAsc(Course course);

}
