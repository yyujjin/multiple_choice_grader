package com.sideproject.grading.controller;

import com.sideproject.grading.domain.Question;
import com.sideproject.grading.domain.QuestionRange;
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
        ArrayList<QuestionRange> range = new ArrayList<>();

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question(1));
        questions.add(new Question(2));
        questions.add(new Question(5));
        range.add(new QuestionRange(1, questions));

        ArrayList<Question> questions2 = new ArrayList<>();
        questions2.add(new Question(2));
        questions2.add(new Question(4));
        range.add(new QuestionRange(2, questions2));

        model.addAttribute("questionRange", range);

        return "answer-selection";
    }
}
