package com.example.webquiz.model.dto;

public class Result {

    private final boolean success;

    private final String feedback;

    public Result(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
