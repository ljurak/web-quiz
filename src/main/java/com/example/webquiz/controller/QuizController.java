package com.example.webquiz.controller;

import com.example.webquiz.model.dto.Answer;
import com.example.webquiz.model.dto.QuizCompletionDto;
import com.example.webquiz.model.dto.Result;
import com.example.webquiz.model.dto.NewQuizDto;
import com.example.webquiz.model.dto.QuizDto;
import com.example.webquiz.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quizzes/{id}")
    public QuizDto getQuiz(@PathVariable int id) {
        return quizService.getQuiz(id);
    }

    @GetMapping("/quizzes")
    public Iterable<QuizDto> getAllQuizzes(@RequestParam("page") @Min(0) int page) {
        return quizService.getAllQuizzes(page);
    }

    @PostMapping("/quizzes")
    public QuizDto createQuiz(@RequestBody @Valid NewQuizDto newQuiz, Authentication authentication) {
        String author = ((UserDetails) authentication.getPrincipal()).getUsername();
        return quizService.createQuiz(newQuiz, author);
    }

    @PostMapping("/quizzes/{id}/solve")
    public Result solveQuiz(@PathVariable int id, @RequestBody @Valid Answer answer, Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return quizService.solveQuiz(id, answer, username);
    }

    @GetMapping("/quizzes/completed")
    public Iterable<QuizCompletionDto> getQuizCompletions(@RequestParam("page") @Min(0) int page, Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return quizService.getQuizCompletions(page, username);
    }

    @DeleteMapping("/quizzes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable int id, Authentication authentication) {
        String author = ((UserDetails) authentication.getPrincipal()).getUsername();
        quizService.deleteQuiz(id, author);
    }
}
