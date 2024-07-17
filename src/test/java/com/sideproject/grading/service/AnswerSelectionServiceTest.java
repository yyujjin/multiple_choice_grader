package com.sideproject.grading.service;

import com.sideproject.grading.domain.SelectedAnswer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerSelectionServiceTest {
    private AnswerSelectionService answerSelectionService;

    @BeforeEach
    public void beforeEach() {
        answerSelectionService = new AnswerSelectionService();
    }

    @Test
    void 선택한_답안_가져오기() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("1_answer", "1");
        parameters.put("2_answer", "4");

        Map<Integer, SelectedAnswer> result = answerSelectionService.getSelectedAnswers(parameters);

        assertThat(result.get(1).getAnswer()).isEqualTo(1);
        assertThat(result.get(2).getAnswer()).isEqualTo(4);
    }

    @Test
    void 다음_페이지_가져오기() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("page", "5");
        parameters.put("pageType", "next");

        int page = answerSelectionService.getPage(parameters);

        assertThat(page).isEqualTo(6);
    }

    @Test
    void 이전_페이지_가져오기() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("page", "2");
        parameters.put("pageType", "prev");

        int page = answerSelectionService.getPage(parameters);

        assertThat(page).isEqualTo(1);
    }
}
