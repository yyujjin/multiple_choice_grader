package com.sideproject.grading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradingController {
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
}
