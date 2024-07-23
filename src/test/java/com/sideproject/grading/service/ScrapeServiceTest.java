package com.sideproject.grading.service;

import com.sideproject.grading.domain.Question;
import com.sideproject.grading.domain.QuestionManager;
import com.sideproject.grading.domain.ScrapeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ScrapeServiceTest {
    private ScrapeService scrapeService;
    List<Integer> confusingList;
    List<Integer> unknownList;
    Map<Integer, Question> scrapedAnswers;

    @BeforeEach
    public void beforEach() {
        scrapeService = new ScrapeService();
    }

    @Test
    void 스크랩문제_가져오기() {
        Map<Integer, Question> selectedScrapeAnswers = QuestionManager.getSelectedScrapAnswers();
        selectedScrapeAnswers.put(1, new Question(ScrapeType.confusing));
        selectedScrapeAnswers.put(3, new Question(ScrapeType.unknown));
        selectedScrapeAnswers.put(4, new Question(ScrapeType.unknown));

        this.scrapedAnswers = QuestionManager.getSelectedScrapAnswers();

        List<Integer> confusingList = new ArrayList<>();
        List<Integer> unknownList = new ArrayList<>();

        Set<Map.Entry<Integer, Question>> entries = scrapedAnswers.entrySet();
        for (Map.Entry<Integer, Question> entry : entries) {
            //알쏭달쏭 버튼
            if (entry.getValue().getScrapeType()==ScrapeType.confusing) {
                confusingList.add(entry.getKey());
                this.confusingList = confusingList;
            }
            //모르겠다 버튼
            if (entry.getValue().getScrapeType()==ScrapeType.unknown) {
                unknownList.add(entry.getKey());
                this.unknownList = unknownList;
            }
        }
        assertThat(confusingList).containsExactlyInAnyOrder(1);
        assertThat(unknownList).containsExactlyInAnyOrder(3,4);
    }
}
