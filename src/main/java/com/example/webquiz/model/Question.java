package com.example.webquiz.model;

public class Question {

    private final String title;

    private final String text;

    private final String[] options;

    public Question(String title, String text, String[] answers) {
        this.title = title;
        this.text = text;
        this.options = answers;
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
}
