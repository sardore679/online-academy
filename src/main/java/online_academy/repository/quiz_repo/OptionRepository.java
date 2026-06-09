package online_academy.repository.quiz_repo;

import online_academy.entity.Option;
import online_academy.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findByQuestion(Question question);

}
