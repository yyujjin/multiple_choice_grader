package com.sideproject.grading.domain;

import java.util.List;

public class  SelectedAnswerManager {
    private static List<SelectedAnswer> selectedAnswers;

    public static List<SelectedAnswer> getSelectedAnswers() {
        return selectedAnswers;
    }

    public static void setSelectedAnswers(List<SelectedAnswer> selectedAnswers) {
        SelectedAnswerManager.selectedAnswers = selectedAnswers;
    }


}
