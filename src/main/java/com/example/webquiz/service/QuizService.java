package com.example.webquiz.service;

import com.example.webquiz.exception.QuizNotFoundException;
import com.example.webquiz.model.Answer;
import com.example.webquiz.model.entity.Quiz;
import com.example.webquiz.model.entity.QuizCompletion;
import com.example.webquiz.model.QuizCompletionDto;
import com.example.webquiz.model.Result;
import com.example.webquiz.model.entity.User;
import com.example.webquiz.model.dto.NewQuizDto;
import com.example.webquiz.repository.QuizCompletionRepo;
import com.example.webquiz.repository.QuizRepo;
import com.example.webquiz.repository.UserRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class QuizService {

    private static final int PAGE_SIZE = 10;

    private final QuizRepo quizRepo;

    private final UserRepo userRepo;

    private final QuizCompletionRepo quizCompletionRepo;

    public QuizService(QuizRepo quizRepo, UserRepo userRepo, QuizCompletionRepo quizCompletionRepo) {
        this.quizRepo = quizRepo;
        this.userRepo = userRepo;
        this.quizCompletionRepo = quizCompletionRepo;
    }

    public Quiz getQuiz(int id) {
        return quizRepo.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found for id: " + id));
    }

    public Iterable<Quiz> getAllQuizzes(int page) {
        return quizRepo.findAll(PageRequest.of(page, PAGE_SIZE));
    }

    public Iterable<QuizCompletionDto> getQuizCompletions(int page, String username) {
        return quizCompletionRepo.findQuizCompletionByUser(username, PageRequest.of(page, PAGE_SIZE));
    }

    @Transactional
    public Quiz createQuiz(NewQuizDto newQuiz, String author) {
        User user = userRepo.findUserByEmail(author).get();
        Quiz quiz = new Quiz(newQuiz.getTitle(), newQuiz.getText(), user, newQuiz.getOptions(), newQuiz.getAnswer());
        return quizRepo.save(quiz);
    }

    @Transactional
    public void deleteQuiz(int quizId, String author) {
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found for id: " + quizId));

        if (!quiz.getAuthor().getEmail().equals(author)) {
            throw new AccessDeniedException("Forbidden");
        }

        quizRepo.delete(quiz);
    }

    @Transactional
    public Result solveQuiz(int quizId, Answer answer, String username) {
        User user = userRepo.findUserByEmail(username).get();
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found for id: " + quizId));

        int[] quizAnswer = quiz.getAnswer();
        int[] userAnswer = answer.getAnswer();

        boolean quizCompleted = false;

        if (quizAnswer == null && userAnswer.length == 0) {
            quizCompleted = true;
        }

        if (quizAnswer != null) {
            Arrays.sort(quizAnswer);
            Arrays.sort(userAnswer);

            if (Arrays.equals(quizAnswer, userAnswer)) {
                quizCompleted = true;
            }
        }

        if (quizCompleted) {
            QuizCompletion quizCompletion = new QuizCompletion(user, quiz);
            quizCompletionRepo.save(quizCompletion);
            return new Result(true, "Congratulations, you're right!");
        }

        return new Result(false, "Wrong answer! Please, try again.");
    }
}
