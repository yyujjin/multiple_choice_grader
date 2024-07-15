package com.sideproject.grading.service;

import com.sideproject.grading.domain.PagingType;
import com.sideproject.grading.domain.SelectedAnswer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Service
public class AnswerSelectionService {

    @Autowired
    public AnswerSelectionService() {
    }

    public List<SelectedAnswer> getSelectedAnswers(HttpServletRequest request) {
        List<SelectedAnswer> selectedAnswers = new ArrayList<>();

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.endsWith("_answer")) {
                String questionNumberStr = paramName.split("_")[0];
                int questionNumber = Integer.parseInt(questionNumberStr);
                int answerNumber = Integer.parseInt(request.getParameter(paramName));

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
