package com.sideproject.grading.service;

import com.sideproject.grading.domain.Question;
import com.sideproject.grading.domain.QuestionManager;

import com.sideproject.grading.domain.ScrapeType;
import com.sideproject.grading.domain.SelectedAnswer;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Service
public class ScrapeService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    List<Integer> confusingList;
    List<Integer> unknownList;

    public Map<Integer, Question> getScrapedAnswers(Map<String, String> parameters) {

        Map<Integer, Question> selectedScrapeAnswers = QuestionManager.getSelectedScrapAnswers();
        int questionNumber = 0;

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            log.info("ENTRY : {}", entry);
            String paramName = entry.getKey();

            if (paramName.endsWith("_mark")) {
                String questionNumberStr = paramName.split("_")[0];
                questionNumber = Integer.parseInt(questionNumberStr);
                //알쏭달쏭이면 처리하는 로직
                if (entry.getValue().equals("confusing")) {
                    selectedScrapeAnswers.put(questionNumber, new Question(ScrapeType.confusing));
                }
                //모르겠다이면 처리하는 로직
                if (entry.getValue().equals("unknown")) {
                    selectedScrapeAnswers.put(questionNumber, new Question(ScrapeType.unknown));
                }
            }
        }

        log.info("selectedScrapeAnswers : {}", selectedScrapeAnswers);

        return selectedScrapeAnswers;
    }

    //알쏭달쏭이랑 모르겠다 분리하는 메서드
    public void splitScrapeType() {

        List<Integer> confusingList = new ArrayList<>();
        List<Integer> unknownList = new ArrayList<>();

        Map<Integer, Question> scrapedAnswers = QuestionManager.getSelectedScrapAnswers();

        Set<Map.Entry<Integer, Question>> entries = scrapedAnswers.entrySet();
        for (Map.Entry<Integer, Question> entry : entries) {
            //알쏭달쏭 버튼
            if (entry.getValue().getScrapeType() == ScrapeType.confusing) {
                confusingList.add(entry.getKey());
                this.confusingList = confusingList;
            }
            //모르겠다 버튼
            if (entry.getValue().getScrapeType() == ScrapeType.unknown) {
                unknownList.add(entry.getKey());
                this.unknownList = unknownList;
            }
        }

    }

    //알쏭달쏭 리스트 내보내는 메서드
    public List<Integer> getConfusing() {
        return confusingList;
    }

    //모르겠다 리스트 내보내는 메서드
    public List<Integer> getUnknown() {
        return unknownList;
    }

    public void resetScrape() {
        //문제 리스트 자체에서 지우기
        QuestionManager.getSelectedScrapAnswers().clear();
        //서비스단 리스트 초기화
        if (this.confusingList != null && this.unknownList != null) {
            confusingList.clear();
            unknownList.clear();
        }

    }
}
