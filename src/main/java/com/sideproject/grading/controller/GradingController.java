package com.sideproject.grading.controller;

import com.sideproject.grading.service.WrongAnswerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GradingController {
    private WrongAnswerService wrongAnswerService;

    @Value("${answer.totalCount}")
    int totalCount;

    @Value("${page.limitCount}")
    int limitCount;

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
        List<Integer> wrongAnswers = wrongAnswerService.getWrongAnswers();

        model.addAttribute("wrongAnswers", wrongAnswers);

        return "wrong-answer";
    }

    @GetMapping("/wrong-answer-again")
    public String solveWrongAnswer(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        List<Integer> wrongAnswers = wrongAnswerService.getWrongAnswers();
        model.addAttribute("questions", wrongAnswers);
        model.addAttribute("page", page);
        model.addAttribute("totalCount", wrongAnswers.size());
        model.addAttribute("limitCount", limitCount);

        return "wrong-answer-again";
    }
}
