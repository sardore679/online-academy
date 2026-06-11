package online_academy.service.comment_service;


import lombok.RequiredArgsConstructor;

import online_academy.dto.comment_dto.CommentRequestDto;
import online_academy.dto.comment_dto.CommentResponseDto;
import online_academy.entity.Comment;
import online_academy.entity.Lesson;
import online_academy.entity.User;
import online_academy.repository.CommentRepository;
import online_academy.repository.LessonRepository;
import online_academy.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    private CommentResponseDto toResponse(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getLesson().getId(),
                comment.getLesson().getTitle(),
                comment.getUser().getId(),
                comment.getUser().getUsername(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }


    @Override
    @Transactional
    public CommentResponseDto addComment(CommentRequestDto request, Long userId) {
        Lesson lesson = lessonRepository.findById(request.lessonId())
                .orElseThrow(() -> new RuntimeException("Lesson topilmadi"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User topilmadi"));

        Comment comment = Comment.builder()
                .content(request.content())
                .lesson(lesson)
                .user(user)
                .build();

        Comment saved = commentRepository.save(comment);

        return toResponse(saved);
    }

    @Override
    @Transactional
    public CommentResponseDto updateComment(Long commentId, String newContent, Long userId) {

        Comment comment = commentRepository.findByIdAndUserId(commentId, userId)
                .orElseThrow(() -> new RuntimeException("Comment topilmadi yoki sizniki emas"));

        comment.setContent(newContent);

        Comment updated = commentRepository.save(comment);

        return toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, Long userId) {

        Comment comment = commentRepository.findByIdAndUserId(commentId, userId)
                .orElseThrow(() -> new RuntimeException("Comment topilmadi yoki sizniki emas"));

        commentRepository.delete(comment);

    }

    @Override
    public List<CommentResponseDto> getCommentsByLesson(Long lessonId) {

        return commentRepository.findByLessonIdOrderByCreatedAtDesc(lessonId)
                .stream()
                .map(this::toResponse)
                .toList();

    }

    @Override
    public List<CommentResponseDto> getMyComments(Long userId) {
        return commentRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

}
