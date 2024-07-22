package com.sideproject.grading.service;

import com.sideproject.grading.domain.Question;
import com.sideproject.grading.domain.QuestionManager;
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
        selectedScrapeAnswers.put(1, new Question("Confusing", true));
        selectedScrapeAnswers.put(2, new Question("Confusing", false));
        selectedScrapeAnswers.put(3, new Question("Unknown", false));
        selectedScrapeAnswers.put(4, new Question("Unknown", true));

        this.scrapedAnswers = QuestionManager.getSelectedScrapAnswers();

        List<Integer> confusingList = new ArrayList<>();
        List<Integer> unknownList = new ArrayList<>();

        Set<Map.Entry<Integer, Question>> entries = scrapedAnswers.entrySet();
        for (Map.Entry<Integer, Question> entry : entries) {
            //알쏭달쏭 버튼
            if(entry.getValue().getScrapeType().equals("Confusing") && entry.getValue().getIsScrap()){
                confusingList.add(entry.getKey());
                //이렇게 안하면 다른 메서드에서 인식못하니 this 해줘야함
                //안해주면 여기 스코프 안에서만 리스트 만들어짐
                this.confusingList = confusingList;
            }
            //모르겠다 버튼
            if(entry.getValue().getScrapeType().equals("Unknown") && entry.getValue().getIsScrap()){
                unknownList.add(entry.getKey());
                //이렇게 안하면 다른 메서드에서 인식못하니 this 해줘야함
                //안해주면 여기 스코프 안에서만 리스트 만들어짐
                this.unknownList = unknownList;
            }
        }
        assertThat(confusingList).containsExactlyInAnyOrder(1);
        assertThat(unknownList).containsExactlyInAnyOrder(4);
    }
}
