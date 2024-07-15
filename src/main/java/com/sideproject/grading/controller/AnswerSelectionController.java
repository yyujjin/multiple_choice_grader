package com.sideproject.grading.controller;

import com.sideproject.grading.domain.SelectedAnswer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
public class AnswerSelectionController {
    @GetMapping("/answer-selection")
    public String answerSelection() {
        return "answer-selection";
    }

    @PostMapping("/questions/next")
    public String next(HttpServletRequest request, Model model) {
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

        return "answer-selection";
    }

}
