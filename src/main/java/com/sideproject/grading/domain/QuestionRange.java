package com.sideproject.grading.domain;

import java.util.ArrayList;

public class QuestionRange {
    private static ArrayList<Chapter> chapters;

    public static ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public static void setChapters(ArrayList<Chapter> chapters) {
        QuestionRange.chapters = chapters;
    }
}
