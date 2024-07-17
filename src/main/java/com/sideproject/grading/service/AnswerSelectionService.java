package com.sideproject.grading.service;

import com.sideproject.grading.domain.SelectedAnswer;
import com.sideproject.grading.domain.SelectedAnswerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AnswerSelectionService {
    int page;
    String pageType;

    @Autowired
    public AnswerSelectionService() {
    }

    public void setPage(String page) {
        this.page = Integer.parseInt(page);
    }

    public int getPage() {
        return page;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public Map<Integer, SelectedAnswer> getSelectedAnswers(Map<String, String> parameters) {
        Map<Integer, SelectedAnswer> selectedAnswers = SelectedAnswerManager.getSelectedAnswers();

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

    public int getNextPage() {
        if (pageType.equals("prev")) {
            return --page;
        }

        return ++page;
    }

    public boolean hasNext(int page, int total, int limit) {
        return page < Math.ceil((double) total / limit);
    }
}
