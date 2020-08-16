package com.example.webquiz.model.dto;

import java.util.List;

public class QuizDto {

    private final Integer id;

    private final String title;

    private final String text;

    private final List<String> options;

    public QuizDto(Integer id, String title, String text, List<String> options) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }
}
