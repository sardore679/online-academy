package online_academy.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online_academy.dto.comment_dto.CommentRequestDto;
import online_academy.dto.comment_dto.CommentResponseDto;
import online_academy.entity.User;
import online_academy.service.comment_service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> addComment(@Valid @RequestBody CommentRequestDto request, @AuthenticationPrincipal User currentUser) {
        CommentResponseDto response = commentService.addComment(request, currentUser.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id,
                                                            @RequestParam String newContent,
                                                            @AuthenticationPrincipal User currentUser) {
        CommentResponseDto response = commentService.updateComment(id, newContent, currentUser.getId());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long id,
            @AuthenticationPrincipal User currentUser) {
        commentService.deleteComment(id, currentUser.getId());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByLesson(@PathVariable Long lessonId) {
        return ResponseEntity.ok(commentService.getCommentsByLesson(lessonId));
    }

    @GetMapping("/my")
    public ResponseEntity<List<CommentResponseDto>> getMyComments(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(commentService.getMyComments(currentUser.getId()));
    }



}
