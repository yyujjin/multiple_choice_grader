package com.sideproject.grading.domain;

import java.util.ArrayList;

public class Chapter {
    private int chapter;
    private ArrayList<Question> questions;

    public Chapter(int chapter, ArrayList<Question> questions) {
        this.chapter = chapter;
        this.questions = questions;
    }

    public int getChapter() {
        return chapter;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
