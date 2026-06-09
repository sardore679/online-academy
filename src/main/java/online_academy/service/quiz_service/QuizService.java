package online_academy.service.quiz_service;


import lombok.RequiredArgsConstructor;
import online_academy.dto.quiz_dto.*;
import online_academy.entity.*;
import online_academy.entity.enems.QuizStatus;
import online_academy.repository.LessonRepository;
import online_academy.repository.UserRepository;
import online_academy.repository.quiz_repo.OptionRepository;
import online_academy.repository.quiz_repo.QuestionRepository;
import online_academy.repository.quiz_repo.QuizRepository;
import online_academy.repository.quiz_repo.QuizResultRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final QuizResultRepository quizResultRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi!"));
    }

    private QuizResponseDto toQuizDto(Quiz quiz) {
        return new QuizResponseDto(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getDurationMinutes(),
                quiz.getPassingScore(),
                quiz.getStatus(),
                quiz.getLesson().getTitle()
        );
    }

    private QuestionResponseDto toQuestionDto(Question question) {
        List<OptionResponseDto> options = question.getOptions()
                .stream()
                .map(o -> new OptionResponseDto(o.getId(), o.getText()))
                .toList();
        return new QuestionResponseDto(
                question.getId(),
                question.getText(),
                options
        );
    }

    private QuizResultResponseDto toResultDto(QuizResult result) {
        return new QuizResultResponseDto(
                result.getId(),
                result.getStudent().getFullName(),
                result.getQuiz().getTitle(),
                result.getScore(),
                result.getTotalQuestions(),
                result.getPassed(),
                result.getSubmittedAt()
        );
    }

    public QuizResponseDto createQuiz(QuizRequestDto dto) {
        Lesson lesson = lessonRepository.findById(dto.lessonId())
                .orElseThrow(() -> new RuntimeException("Dars topilmadi: " + dto.lessonId()));

        Quiz quiz = new Quiz();
        quiz.setTitle(dto.title());
        quiz.setDurationMinutes(dto.durationMinutes());
        quiz.setPassingScore(dto.passingScore());
        quiz.setStatus(dto.status());
        quiz.setLesson(lesson);

        return toQuizDto(quizRepository.save(quiz));
    }

    public QuestionResponseDto addQuestion(QuestionRequestDto dto) {
        Quiz quiz = quizRepository.findById(dto.quizId())
                .orElseThrow(() -> new RuntimeException("Quiz topilmadi: " + dto.quizId()));

        Question question = new Question();
        question.setText(dto.text());
        question.setQuiz(quiz);
        Question saved = questionRepository.save(question);

        List<Option> options = dto.options().stream().map(optDto -> {
            Option option = new Option();
            option.setText(optDto.text());
            option.setIsCorrect(optDto.isCorrect());
            option.setQuestion(saved);
            return option;
        }).toList();
        optionRepository.saveAll(options);

        saved.setOptions(options);

        return toQuestionDto(saved);
    }

    public List<QuestionResponseDto> getQuizQuestions(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz topilmadi: " + quizId));

        return questionRepository.findByQuiz(quiz)
                .stream()
                .map(this::toQuestionDto)
                .toList();
    }

    public QuizResultResponseDto submitQuiz(Long quizId, QuizSubmitDto dto) {
        User student = getCurrentUser();

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz topilmadi: " + quizId));

        if (quiz.getStatus() != QuizStatus.ACTIVE) {
            throw new RuntimeException("Quiz hozir faol emas!");
        }

        List<Question> questions = questionRepository.findByQuiz(quiz);
        int totalQuestions = questions.size();
        int correctCount = 0;

        for (Question question : questions) {
            Long selectOptionId = dto.answers().get(question.getId());
            if (selectOptionId != null) {
                Option selectedOption = optionRepository.findById(selectOptionId)
                        .orElse(null);
                if (selectedOption != null && selectedOption.getIsCorrect()) {
                    correctCount++;
                }
            }
        }

        boolean passed = correctCount >= quiz.getPassingScore();

        QuizResult result = new QuizResult();
        result.setStudent(student);
        result.setQuiz(quiz);
        result.setScore(correctCount);
        result.setTotalQuestions(totalQuestions);
        result.setPassed(passed);

        return toResultDto(quizResultRepository.save(result));
    }

    public List<QuizResultResponseDto> getLeaderboard(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz Topilmadi: " + quizId));

        return quizResultRepository.findByQuizOrderByScoreDesc(quiz)
                .stream()
                .map(this::toResultDto)
                .toList();
    }

    public QuizResultResponseDto getMyResult(long quizId) {
        User student = getCurrentUser();
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz topilmadi: " + quizId));
        QuizResult result = quizResultRepository.findByStudentAndQuiz(student, quiz)
                .orElseThrow(() -> new RuntimeException("Siz bu quizni topshirmagansiz!"));

        return toResultDto(result);

    }

    public List<QuizResultResponseDto> getAllResults(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz topilmadi: " + quizId));
        return quizResultRepository.findByQuizOrderByScoreDesc(quiz)
                .stream()
                .map(this::toResultDto)
                .toList();
    }



}
