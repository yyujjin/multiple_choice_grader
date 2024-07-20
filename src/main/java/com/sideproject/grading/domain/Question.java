package com.sideproject.grading.domain;


public class Question {
    private int number;
    //알쏭달쏭
    private boolean isScrap;
    //모르겠다
    private boolean isWrong;

    public Question(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
