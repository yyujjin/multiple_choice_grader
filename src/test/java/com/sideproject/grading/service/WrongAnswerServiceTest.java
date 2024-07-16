package com.sideproject.grading.service;

import com.sideproject.grading.domain.SelectedAnswer;
import com.sideproject.grading.domain.SelectedAnswerManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WrongAnswerServiceTest {
    private WrongAnswerService wrongAnswerService;

    @BeforeEach
    public void beforeEach() {
        wrongAnswerService = new WrongAnswerService();
    }

    @Test
    public void 틀린문제_가져오기() {
        List<SelectedAnswer> testAnswers = new ArrayList<>();
        testAnswers.add(new SelectedAnswer(1, 2));
        testAnswers.add(new SelectedAnswer(2, 4));
        testAnswers.add(new SelectedAnswer(3, 2));
        SelectedAnswerManager.setSelectedAnswers(testAnswers);

        List<Integer> wrongAnswers = wrongAnswerService.getWrongAnswers();

        assertThat(wrongAnswers.size()).isEqualTo(2);
    }
}
