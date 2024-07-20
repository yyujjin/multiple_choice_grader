package com.sideproject.grading.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class QuestionManager {

    //배열 전역변수로 만들기
    private  static Map<Integer, Question> selectedScrapedAnswers = new HashMap<Integer, Question>();
}
