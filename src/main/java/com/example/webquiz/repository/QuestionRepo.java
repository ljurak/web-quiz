package com.example.webquiz.repository;

import com.example.webquiz.model.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionRepo {

    private List<Question> questions = new ArrayList<>();

    public Question getQuestion() {
        return questions.get(0);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
