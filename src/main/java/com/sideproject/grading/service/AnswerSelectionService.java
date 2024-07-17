package com.sideproject.grading.service;

import com.sideproject.grading.domain.SelectedAnswer;
import com.sideproject.grading.domain.SelectedAnswerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerSelectionService {

    @Autowired
    public AnswerSelectionService() {
    }

    public Map<Integer, SelectedAnswer> getSelectedAnswers(Map<String, String> parameters) {
        Map<Integer, SelectedAnswer> selectedAnswers = new HashMap<>();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String paramName = entry.getKey();
            if (paramName.endsWith("_answer")) {
                String questionNumberStr = paramName.split("_")[0];
                int questionNumber = Integer.parseInt(questionNumberStr);
                int answerNumber = Integer.parseInt(entry.getValue());

                selectedAnswers.put(questionNumber, new SelectedAnswer(questionNumber, answerNumber));
            }
        }

        return selectedAnswers;
    }

    public int getPage(Map<String, String> parameters) {
        int page = 1;
        String pageType = "next";

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String paramName = entry.getKey();
            if (paramName.equals("page")) {
                page = Integer.parseInt(entry.getValue());
            } else if (paramName.equals("pageType")) {
                pageType = entry.getValue();
            }
        }

        if (pageType.equals("prev")) {
            return --page;
        }

        return ++page;
    }
}
