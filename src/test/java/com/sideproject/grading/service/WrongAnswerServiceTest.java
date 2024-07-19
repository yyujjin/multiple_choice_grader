package com.sideproject.grading.service;

import com.sideproject.grading.domain.SelectedAnswer;
import com.sideproject.grading.domain.SelectedAnswerManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WrongAnswerServiceTest {
    private WrongAnswerService wrongAnswerService;

    @BeforeEach
    public void beforeEach() {
        wrongAnswerService = new WrongAnswerService();
    }

    @Test
    public void 틀린문제_가져오기() {
        Map<Integer, SelectedAnswer> testAnswers = new HashMap<>();
        testAnswers.put(1, new SelectedAnswer(1, 2));
        testAnswers.put(2, new SelectedAnswer(2, 4));
        testAnswers.put(3, new SelectedAnswer(3, 3));
        SelectedAnswerManager.setSelectedAnswers(testAnswers);

        wrongAnswerService.setWrongAnswers();
        List<Integer> wrongAnswers = wrongAnswerService.getWrongAnswers();

        assertThat(wrongAnswers.size()).isEqualTo(2);
    }
}
