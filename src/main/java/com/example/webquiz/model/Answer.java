package com.example.webquiz.model;

import javax.validation.constraints.NotNull;

public class Answer {

    @NotNull
    private int[] answer;

    public Answer() {
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
