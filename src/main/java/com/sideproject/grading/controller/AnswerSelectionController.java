package com.sideproject.grading.controller;

import com.sideproject.grading.domain.SelectedAnswer;
import com.sideproject.grading.domain.SelectedAnswerManager;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
public class AnswerSelectionController {
    @GetMapping("/answer-selection")
    public String answerSelection(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        model.addAttribute("page", page);

        return "answer-selection";
    }

    @PostMapping("/questions/next")
    public String next(HttpServletRequest request, Model model) {
        List<SelectedAnswer> selectedAnswers = new ArrayList<>();
        int nextPage = 1;

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.endsWith("_answer")) {
                String questionNumberStr = paramName.split("_")[0];
                int questionNumber = Integer.parseInt(questionNumberStr);
                int answerNumber = Integer.parseInt(request.getParameter(paramName));

                selectedAnswers.add(new SelectedAnswer(questionNumber, answerNumber));
            } else if (paramName.equals("page")) {
                nextPage = Integer.parseInt(request.getParameter(paramName)) + 1;
            }
        }

        SelectedAnswerManager.setSelectedAnswers(selectedAnswers);

        if (nextPage > 3) {
            return "redirect:/result";
        }

        return "redirect:/answer-selection?page="+nextPage;
    }

}
