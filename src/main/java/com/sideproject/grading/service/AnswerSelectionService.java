package com.sideproject.grading.service;

import com.sideproject.grading.domain.PagingType;
import com.sideproject.grading.domain.SelectedAnswer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Service
public class AnswerSelectionService {

    @Autowired
    public AnswerSelectionService() {
    }

    public List<SelectedAnswer> getSelectedAnswers(Map<String, String> parameters) {
        List<SelectedAnswer> selectedAnswers = new ArrayList<>();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String paramName = entry.getKey();
            if (paramName.endsWith("_answer")) {
                String questionNumberStr = paramName.split("_")[0];
                int questionNumber = Integer.parseInt(questionNumberStr);
                int answerNumber = Integer.parseInt(entry.getValue());

                selectedAnswers.add(new SelectedAnswer(questionNumber, answerNumber));
            }
        }

        return selectedAnswers;
    }

    public int getPage(HttpServletRequest request, PagingType type) {
        int page = 1;

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.equals("page")) {
                page = Integer.parseInt(request.getParameter(paramName));
            }
        }

        if (type == PagingType.PREV) {
            return --page;
        }

        return ++page;
    }
}
