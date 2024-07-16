package com.sideproject.grading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradingController {
    @GetMapping("/")
    public String start() {
        return "start";
    }

    @GetMapping("/range-selection")
    public String rangeSelection() {
        return "range-selection";
    }

    @GetMapping("/wrong-answer")
    public String wrongAnswer() {
        return "wrong-answer";
    }
}
