package com.sideproject.grading.service;

import com.sideproject.grading.domain.CorrectAnswers;
import com.sideproject.grading.domain.SelectedAnswer;
import com.sideproject.grading.domain.SelectedAnswerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WrongAnswerService {
    @Autowired
    public WrongAnswerService() {}

    public List<Integer> getWrongAnswers() {
        List<Integer> wrongAnswers = new ArrayList<>();

        Map<Integer, Integer> correctAnswers = CorrectAnswers.getAnswers();

        List<SelectedAnswer> selectedAnswers = SelectedAnswerManager.getSelectedAnswers();

        selectedAnswers.forEach(selectedAnswer -> {
            int answer = selectedAnswer.getAnswer();
            int correctAnswer = correctAnswers.get(selectedAnswer.getNumber());
            if (answer != correctAnswer) {
                wrongAnswers.add(selectedAnswer.getNumber());
            }
        });

        return wrongAnswers;
    }
}
