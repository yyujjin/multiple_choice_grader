package com.sideproject.grading.domain;

import java.util.HashMap;
import java.util.Map;

public class QuestionManager {

    //배열 전역변수로 만들기
    private  static Map<Integer, Question> selectedScrapAnswers = new HashMap<Integer, Question>();

    public static Map<Integer, Question> getSelectedScrapAnswers() {
        return selectedScrapAnswers;
    }

    public static void setSelectedScrapAnswers(Map<Integer, Question> selectedScrapedAnswers) {
        QuestionManager.selectedScrapAnswers = selectedScrapedAnswers;
    }
}
