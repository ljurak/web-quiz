package com.example.webquiz.model.mapper;

import com.example.webquiz.model.dto.NewQuizDto;
import com.example.webquiz.model.dto.QuizDto;
import com.example.webquiz.model.entity.Quiz;
import com.example.webquiz.model.entity.User;

public class QuizMapper {

    public static Quiz toEntity(NewQuizDto newQuiz, User author) {
        return new Quiz(
                newQuiz.getTitle(),
                newQuiz.getText(),
                author,
                newQuiz.getOptions(),
                newQuiz.getAnswer()
        );
    }

    public static QuizDto toDto(Quiz quiz) {
        return new QuizDto(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getText(),
                quiz.getOptions()
        );
    }
}
