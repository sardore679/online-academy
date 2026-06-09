package online_academy.controller.quiz;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online_academy.dto.quiz_dto.*;
import online_academy.service.quiz_service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public ResponseEntity<QuizResponseDto> createQuiz(@Valid @RequestBody QuizRequestDto dto) {
        return ResponseEntity.ok(quizService.createQuiz(dto));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/questions")
    public ResponseEntity<QuestionResponseDto> addQuestion(@Valid @RequestBody QuestionRequestDto dto) {
        return ResponseEntity.ok(quizService.addQuestion(dto));
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<List<QuestionResponseDto>> getQuizQuestions(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizQuestions(id));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/{id}/submit")
    public ResponseEntity<QuizResultResponseDto> submitQuiz(@PathVariable Long id, @Valid @RequestBody QuizSubmitDto dto) {
        return ResponseEntity.ok(quizService.submitQuiz(id, dto));
    }

    @GetMapping("/{id}/leaderboard")
    public ResponseEntity<List<QuizResultResponseDto>> getLeaderboard(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getLeaderboard(id));
    }

    @GetMapping("{id}/my-result")
    public ResponseEntity<QuizResultResponseDto> getMyResult(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getMyResult(id));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/{id}/results")
    public ResponseEntity<List<QuizResultResponseDto>> getAllResults(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getAllResults(id));
    }


}
