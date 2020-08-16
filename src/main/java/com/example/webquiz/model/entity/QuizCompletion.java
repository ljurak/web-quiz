package com.example.webquiz.model.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class QuizCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "QUIZ_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Quiz quiz;

    private LocalDateTime completedAt;

    public QuizCompletion() {
    }

    public QuizCompletion(User user, Quiz quiz) {
        this.user = user;
        this.quiz = quiz;
        this.completedAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}
