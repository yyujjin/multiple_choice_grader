package com.sideproject.grading.controller;

import com.sideproject.grading.domain.SelectedAnswerManager;
import com.sideproject.grading.service.AnswerSelectionService;
import com.sideproject.grading.service.WrongAnswerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class GradingController {
    private final AnswerSelectionService answerSelectionService;
    private final WrongAnswerService wrongAnswerService;

    @Value("${answer.totalCount}")
    int totalCount;

    @Value("${page.limitCount}")
    int limitCount;

    public GradingController(AnswerSelectionService answerSelectionService, WrongAnswerService wrongAnswerService) {
        this.answerSelectionService = answerSelectionService;
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
    public String result(Model model) {
        model.addAttribute("totalCount",totalCount);
        return "result";
    }

    @GetMapping("/answer-selection")
    public String answerSelection(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        answerSelectionService.setQuestions(totalCount);

        model.addAttribute("questions", answerSelectionService.sliceAnswers(page, limitCount));
        model.addAttribute("action", "/questions/next");
        model.addAttribute("page", page);
        model.addAttribute("hasNext", answerSelectionService.hasNext(page, totalCount, limitCount));

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

        answerSelectionService.setPage(parameters.get("page"));
        answerSelectionService.setPageType(parameters.get("pageType"));

        SelectedAnswerManager.setSelectedAnswers(answerSelectionService.getSelectedAnswers(parameters));

        if (!answerSelectionService.hasNext(answerSelectionService.getPage(), totalCount, limitCount)) {
            return "redirect:/result";
        }

        return "redirect:/answer-selection?page=" + answerSelectionService.getNextPage();
    }

    @GetMapping("/wrong-answer")
    public String wrongAnswer(Model model) {
        wrongAnswerService.setWrongAnswers();

        model.addAttribute("wrongAnswers", wrongAnswerService.getWrongAnswers());

        return "wrong-answer";
    }

    @GetMapping("/wrong-answer-again")
    public String solveWrongAnswer(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
//        wrongAnswerService.setWrongAnswers();
        int total = wrongAnswerService.getWrongAnswers().size();

        model.addAttribute("questions", wrongAnswerService.sliceWrongAnswers(page, limitCount));
        model.addAttribute("action", "/wrong-questions/next");
        model.addAttribute("page", page);
        model.addAttribute("hasNext", answerSelectionService.hasNext(page, total, limitCount));

        return "answer-selection";
    }

    @PostMapping("/wrong-questions/next")
    public String nextWrongQuestions(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            parameters.put(paramName, request.getParameter(paramName));
        }

        answerSelectionService.setPage(parameters.get("page"));
        answerSelectionService.setPageType(parameters.get("pageType"));

        SelectedAnswerManager.setSelectedAnswers(answerSelectionService.getSelectedAnswers(parameters));

        int total = wrongAnswerService.getWrongAnswers().size();

        if (!answerSelectionService.hasNext(answerSelectionService.getPage(), total, limitCount)) {
            return "redirect:/result";
        }

        return "redirect:/wrong-answer-again?page=" + answerSelectionService.getNextPage();
    }

    @GetMapping("/scrape")
    public  String scrape() {
        return "/scrape";
    }
}
