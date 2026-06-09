package online_academy.repository.quiz_repo;

import online_academy.entity.Quiz;
import online_academy.entity.QuizResult;
import online_academy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {

    List<QuizResult> findByStudent(User student);

    List<QuizResult> findByQuizOrderByScoreDesc(Quiz quiz);

    Optional<QuizResult> findByStudentAndQuiz(User student, Quiz quiz);

    boolean existsByStudentAndQuiz(User student, Quiz quiz);

}
