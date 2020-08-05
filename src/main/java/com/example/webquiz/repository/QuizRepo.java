package com.example.webquiz.repository;

import com.example.webquiz.model.Quiz;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class QuizRepo {

    private int currentId = 0;

    private Map<Integer, Quiz> quizzes = new HashMap<>();

    public Optional<Quiz> getQuiz(int id) {
        return Optional.ofNullable(quizzes.get(id));
    }

    public List<Quiz> getAllQuizzes() {
        return new ArrayList<>(quizzes.values());
    }

    public Quiz createQuiz(Quiz quiz) {
        quiz.setId(++currentId);
        quizzes.put(quiz.getId(), quiz);
        return quiz;
    }
}
