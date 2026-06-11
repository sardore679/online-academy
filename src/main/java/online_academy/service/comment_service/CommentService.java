package online_academy.service.comment_service;


import online_academy.dto.comment_dto.CommentRequestDto;
import online_academy.dto.comment_dto.CommentResponseDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto addComment(CommentRequestDto request, Long userId);

    CommentResponseDto updateComment(Long commentId, String newContent, Long userId);

    void deleteComment(Long commentId, Long userId);

    List<CommentResponseDto> getCommentsByLesson(Long lessonId);

    List<CommentResponseDto> getMyComments(Long userId);

}
