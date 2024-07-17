package com.sideproject.grading.service;

import com.sideproject.grading.domain.CorrectAnswers;
import com.sideproject.grading.domain.SelectedAnswer;
import com.sideproject.grading.domain.SelectedAnswerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class WrongAnswerService {
    List<Integer> wrongAnswers;
    @Autowired
    public WrongAnswerService() {}

    public void setWrongAnswers() {
        List<Integer> wrongAnswers = new ArrayList<>();

        Map<Integer, Integer> correctAnswers = CorrectAnswers.getAnswers();

        Map<Integer, SelectedAnswer> selectedAnswers = SelectedAnswerManager.getSelectedAnswers();

        Set<Map.Entry<Integer, SelectedAnswer>> entries = selectedAnswers.entrySet();
        for (Map.Entry<Integer, SelectedAnswer> entry: entries) {
            int correctAnswer = correctAnswers.get(entry.getKey());
            if (entry.getValue().getAnswer() != correctAnswer) {
                wrongAnswers.add(entry.getKey());
            }
        }

        this.wrongAnswers = wrongAnswers;
    }

    public List<Integer> getWrongAnswers() {
        return wrongAnswers;
    }

    public List<Integer> sliceWrongAnswers(int page, int limitCount) {
        int end = page * limitCount;
        if (end > wrongAnswers.size()) {
            end = wrongAnswers.size();
        }
        return wrongAnswers.subList((page - 1) * limitCount, end);
    }
}
