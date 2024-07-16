package com.sideproject.grading.domain;

public class Question {
    private int number;
    private boolean isScrap;
    private boolean isWrong;

    public Question(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
