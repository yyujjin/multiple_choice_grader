package com.sideproject.grading.controller;

import com.sideproject.grading.domain.SelectedAnswerManager;
import com.sideproject.grading.service.AnswerSelectionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AnswerSelectionController {
    private final AnswerSelectionService answerSelectionService;

    @Value("${answer.totalCount}")
    int totalCount;

    @Value("${page.limitCount}")
    int limitCount;

    public AnswerSelectionController(AnswerSelectionService answerSelectionService) {
        this.answerSelectionService = answerSelectionService;
    }

    @GetMapping("/answer-selection")
    public String answerSelection(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        model.addAttribute("page", page);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("limitCount", limitCount);

        return "answer-selection";
    }

    @PostMapping("/questions/next")
    public String next(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            parameters.put(paramName, request.getParameter(paramName));
        }

        SelectedAnswerManager.setSelectedAnswers(answerSelectionService.getSelectedAnswers(parameters));


        int nextPage = answerSelectionService.getPage(parameters);

        if (nextPage > totalCount / limitCount) {
            return "redirect:/result";
        }

        return "redirect:/answer-selection?page=" + nextPage;
    }

}
