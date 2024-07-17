package com.sideproject.grading.domain;

import java.util.HashMap;
import java.util.Map;

public class  SelectedAnswerManager {
    private static Map<Integer, SelectedAnswer> selectedAnswers = new HashMap<>();;

    public static Map<Integer, SelectedAnswer> getSelectedAnswers() {
        return selectedAnswers;
    }

    public static void setSelectedAnswers(Map<Integer, SelectedAnswer> selectedAnswers) {
        SelectedAnswerManager.selectedAnswers = selectedAnswers;
    }


}