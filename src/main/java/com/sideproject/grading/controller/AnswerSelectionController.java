package com.sideproject.grading.controller;

import com.sideproject.grading.domain.Chapter;
import com.sideproject.grading.domain.Question;
import com.sideproject.grading.domain.QuestionRange;
import com.sideproject.grading.domain.SelectedAnswer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
public class AnswerSelectionController {
    @GetMapping("/answer-selection")
    public String answerSelection(Model model) {
        ArrayList<Chapter> chapters = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question(1));
        questions.add(new Question(2));
        questions.add(new Question(5));
        chapters.add(new Chapter(1, questions));

//        ArrayList<Question> questions2 = new ArrayList<>();
//        questions2.add(new Question(2));
//        questions2.add(new Question(4));
//        range.add(new Chapter(2, questions2));

        QuestionRange.setChapters(chapters);

        model.addAttribute("questionRange", chapters);

        return "answer-selection";
    }

    @PostMapping("/questions/next")
    public String next(HttpServletRequest request, Model model) {
        List<SelectedAnswer> selectedAnswers = new ArrayList<>();

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.endsWith("_answer")) {
                String questionNumberStr = paramName.split("_")[0];
                int questionNumber = Integer.parseInt(questionNumberStr);
                int answerNumber = Integer.parseInt(request.getParameter(paramName));

                selectedAnswers.add(new SelectedAnswer(questionNumber, answerNumber));
            }
        }
        ArrayList<Chapter> chapters = QuestionRange.getChapters();

        model.addAttribute("questionRange", chapters);
        return "answer-selection";
    }

}
