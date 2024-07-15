package com.sideproject.grading.domain;

public class SelectedAnswer {
    private int number;
    private int answer;

    public SelectedAnswer(int number, int answer) {
        this.number = number;
        this.answer = answer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
