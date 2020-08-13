package com.example.webquiz.service;

import com.example.webquiz.exception.QuizNotFoundException;
import com.example.webquiz.model.Answer;
import com.example.webquiz.model.Quiz;
import com.example.webquiz.model.Result;
import com.example.webquiz.model.User;
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

    public QuizService(QuizRepo quizRepo, UserRepo userRepo) {
        this.quizRepo = quizRepo;
        this.userRepo = userRepo;
    }

    public Quiz getQuiz(int id) {
        return quizRepo.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found for id: " + id));
    }

    public Iterable<Quiz> getAllQuizzes(int page) {
        return quizRepo.findAll(PageRequest.of(page, PAGE_SIZE));
    }

    @Transactional
    public Quiz createQuiz(Quiz quiz, String author) {
        User user = userRepo.findUserByEmail(author).get();
        quiz.setAuthor(user);
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

    public Result solveQuiz(int quizId, Answer answer) {
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found for id: " + quizId));

        int[] quizAnswer = quiz.getAnswer();
        int[] userAnswer = answer.getAnswer();

        if (quizAnswer == null && userAnswer.length == 0) {
            return new Result(true, "Congratulations, you're right!");
        }

        if (quizAnswer != null) {
            Arrays.sort(quizAnswer);
            Arrays.sort(userAnswer);

            if (Arrays.equals(quizAnswer, userAnswer)) {
                return new Result(true, "Congratulations, you're right!");
            }
        }

        return new Result(false, "Wrong answer! Please, try again.");
    }
}
