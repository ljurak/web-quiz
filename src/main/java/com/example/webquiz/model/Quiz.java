package com.example.webquiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Quiz {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotBlank
    private final String title;

    @NotBlank
    private final String text;

    @NotEmpty
    @Size(min = 2)
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
