package online_academy.repository;


import online_academy.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByLessonIdOrderByCreatedAtDesc(Long lessonId);

    List<Comment> findByUserId(Long userId);

    Long countByLessonId(Long lessonId);

    @Query("SELECT c FROM Comment c WHERE c.id = :commentId AND c.user.id = :userId")
    Optional<Comment> findByIdAndUserId(@Param("commentId") Long commentId,
                                        @Param("userId") Long userId);

}
