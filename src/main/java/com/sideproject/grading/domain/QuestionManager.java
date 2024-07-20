package com.sideproject.grading.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class QuestionManager {

    //배열 전역변수로 만들기
    private  static Map<Integer, Question> selectedScrapedAnswers = new HashMap<Integer, Question>();

    public static Map<Integer, Question> getSelectedScrapedAnswers() {
        return selectedScrapedAnswers;
    }

    public static void setSelectedScrapedAnswers(Map<Integer, Question> selectedScrapedAnswers) {
        QuestionManager.selectedScrapedAnswers = selectedScrapedAnswers;
    }
}
