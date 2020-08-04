package com.example.webquiz.service;

import com.example.webquiz.model.Question;
import com.example.webquiz.model.Result;
import com.example.webquiz.repository.QuestionRepo;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    private final QuestionRepo questionRepo;

    public QuizService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    public Question getQuiz() {
        return questionRepo.getQuestion();
    }

    public Result solveQuiz(int answer) {
        if (answer == 2) {
            return new Result(true, "Congratulations, you're right!");
        }
        return new Result(false, "Wrong answer! Please, try again.");
    }
}
