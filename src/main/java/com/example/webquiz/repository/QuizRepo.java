package com.example.webquiz.repository;

import com.example.webquiz.model.Quiz;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class QuestionRepo {

    private int currentId = 0;

    private Map<Integer, Quiz> quizzes = new HashMap<>();

    public Quiz getQuestion(int id) {
        return quizzes.get(id);
    }

    public Quiz createQuestion(Quiz quiz) {
        quiz.setId(++currentId);
        quizzes.put(quiz.getId(), quiz);
        return quiz;
    }
}
