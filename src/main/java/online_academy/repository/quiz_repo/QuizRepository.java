package online_academy.repository.quiz_repo;

import online_academy.entity.Lesson;
import online_academy.entity.Quiz;
import online_academy.entity.enems.QuizStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByLesson(Lesson lesson);

    List<Quiz> findByStatus(QuizStatus status);

}
