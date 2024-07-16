package com.sideproject.grading.controller;

import com.sideproject.grading.domain.SelectedAnswer;
import com.sideproject.grading.domain.SelectedAnswerManager;
import com.sideproject.grading.service.WrongAnswerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GradingController {
    private WrongAnswerService wrongAnswerService;

    public GradingController(WrongAnswerService wrongAnswerService) {
        this.wrongAnswerService = wrongAnswerService;
    }

    @GetMapping("/")
    public String start() {
        return "start";
    }

    @GetMapping("/range-selection")
    public String rangeSelection() {
        return "range-selection";
    }

    @GetMapping("/wrong-answer")
    public String wrongAnswer(Model model) {
        // TODO answer-selection 에서 받아올 데이터
        List<SelectedAnswer> testAnswers = new ArrayList<>();
        testAnswers.add(new SelectedAnswer(1, 2));
        testAnswers.add(new SelectedAnswer(2, 4));
        testAnswers.add(new SelectedAnswer(3, 2));
        SelectedAnswerManager.setSelectedAnswers(testAnswers);

        List<Integer> wrongAnswers = wrongAnswerService.getWrongAnswers();

        model.addAttribute("wrongAnswers", wrongAnswers);

        return "wrong-answer";
    }
}
