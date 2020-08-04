package com.example.webquiz.controller;

import com.example.webquiz.model.Question;
import com.example.webquiz.model.Result;
import com.example.webquiz.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quiz")
    public Question getQuiz() {
        return quizService.getQuiz();
    }

    @PostMapping(path = "/quiz")
    public Result solveQuiz(@RequestParam int answer) {
        return quizService.solveQuiz(answer);
    }
}
