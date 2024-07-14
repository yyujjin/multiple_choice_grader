package com.sideproject.grading.controller;

import com.sideproject.grading.domain.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

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

    @GetMapping("/answer-selection")
    public String answerSelection(Model model) {
        ArrayList questions = new ArrayList<>();
        questions.add(new Question(1));
        questions.add(new Question(2));
        questions.add(new Question(5));

//        [{chapter: 1, numbers: [1,2,3]}]
        model.addAttribute("questions", questions);

        return "answer-selection";
    }
}
