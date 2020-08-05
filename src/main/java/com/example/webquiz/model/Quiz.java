package com.example.webquiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quiz {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private final String title;

    private final String text;

    private final String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final int answer;

    public Quiz(String title, String text, String[] answers, int answer) {
        this.title = title;
        this.text = text;
        this.options = answers;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public int getAnswer() {
        return answer;
    }
}
