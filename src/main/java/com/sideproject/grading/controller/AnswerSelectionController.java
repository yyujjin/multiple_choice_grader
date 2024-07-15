package com.sideproject.grading.controller;

import com.sideproject.grading.domain.PagingType;
import com.sideproject.grading.domain.SelectedAnswer;
import com.sideproject.grading.domain.SelectedAnswerManager;
import com.sideproject.grading.service.AnswerSelectionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnswerSelectionController {
    private final AnswerSelectionService answerSelectionService;

    public AnswerSelectionController(AnswerSelectionService answerSelectionService) {
        this.answerSelectionService = answerSelectionService;
    }

    @GetMapping("/answer-selection")
    public String answerSelection(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        model.addAttribute("page", page);

        return "answer-selection";
    }

    @PostMapping("/questions/next")
    public String next(HttpServletRequest request) {
        SelectedAnswerManager.setSelectedAnswers(answerSelectionService.getSelectedAnswers(request));

        int nextPage = answerSelectionService.getPage(request, PagingType.NEXT);

        if (nextPage > 3) {
            return "redirect:/result";
        }

        return "redirect:/answer-selection?page=" + nextPage;
    }

    @PostMapping("/questions/prev")
    public String prev(HttpServletRequest request) {
        SelectedAnswerManager.setSelectedAnswers(answerSelectionService.getSelectedAnswers(request));

        int nextPage = answerSelectionService.getPage(request, PagingType.PREV);

        if (nextPage < 1) {
            nextPage = 1;
        }

        return "redirect:/answer-selection?page=" + nextPage;
    }

}
