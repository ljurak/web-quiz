package com.example.webquiz.model;

import java.time.LocalDateTime;

public class QuizCompletionDto {

    private final int quizId;

    private final LocalDateTime completedAt;

    public QuizCompletionDto(int quizId, LocalDateTime completedAt) {
        this.quizId = quizId;
        this.completedAt = completedAt;
    }

    public int getQuizId() {
        return quizId;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}
