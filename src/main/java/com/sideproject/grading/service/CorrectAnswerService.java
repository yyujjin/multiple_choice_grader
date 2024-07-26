package com.sideproject.grading.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorrectAnswerService {

    private static final Logger log = LoggerFactory.getLogger(CorrectAnswerService.class);
    @Autowired
    WrongAnswerService wrongAnswerService;

    public int getCorrectAnswerCount(int totalCount) {
        wrongAnswerService.setWrongAnswers();
        List<Integer> wrongAnswers = wrongAnswerService.getWrongAnswers();
        int correctAnswerCount = totalCount-wrongAnswers.size();
        log.info(" correctAnswerCount : {}", correctAnswerCount);

        return correctAnswerCount;
    }
}
