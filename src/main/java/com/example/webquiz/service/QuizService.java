package com.example.webquiz.service;

import com.example.webquiz.exception.QuizNotFoundException;
import com.example.webquiz.model.Answer;
import com.example.webquiz.model.Quiz;
import com.example.webquiz.model.Result;
import com.example.webquiz.repository.QuizRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuizService {

    private final QuizRepo quizRepo;

    public QuizService(QuizRepo quizRepo) {
        this.quizRepo = quizRepo;
    }

    public Quiz getQuiz(int id) {
        return quizRepo.getQuiz(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found for id: " + id));
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepo.getAllQuizzes();
    }

    public Quiz createQuiz(Quiz quiz) {
        return quizRepo.createQuiz(quiz);
    }

    public Result solveQuiz(int quizId, Answer answer) {
        Quiz quiz = quizRepo.getQuiz(quizId)
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
