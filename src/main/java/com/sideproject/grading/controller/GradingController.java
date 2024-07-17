package com.sideproject.grading.controller;

import com.sideproject.grading.domain.SelectedAnswer;
import com.sideproject.grading.domain.SelectedAnswerManager;
import com.sideproject.grading.service.WrongAnswerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
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

// v2 에서 작업 예정이라 주석 처리
//    @GetMapping("/range-selection")
//    public String rangeSelection() {
//        return "range-selection";
//    }

    @GetMapping("/result")
    public String result() {
        return "result";
    }

    @GetMapping("/wrong-answer")
    public String wrongAnswer(Model model) {
        // TODO answer-selection 에서 받아올 데이터
        Map<Integer, SelectedAnswer> testAnswers = new HashMap<>();
        testAnswers.put(1, new SelectedAnswer(1, 2));
        testAnswers.put(2, new SelectedAnswer(2, 4));
        testAnswers.put(3, new SelectedAnswer(3, 2));
        SelectedAnswerManager.setSelectedAnswers(testAnswers);

        List<Integer> wrongAnswers = wrongAnswerService.getWrongAnswers();

        model.addAttribute("wrongAnswers", wrongAnswers);

        return "wrong-answer";
    }
}
