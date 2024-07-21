package com.sideproject.grading.service;

import com.sideproject.grading.domain.Question;
import com.sideproject.grading.domain.QuestionManager;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@lombok.extern.slf4j.Slf4j
public class ScrapeService {

    //TODO:다시 풀기 누르면 배열 초기화 시키기
    //TODO:문제 번호 별로 정렬하기 SET
    //TODO: 스크랩 서비스 따로 빼야할것같은데
    public Map<Integer, Question> getScrapedAnswers(Map<String, String> parameters) {

        //배열에 이미 저장돼 있는 거 가져오고
        Map<Integer, Question> selectedScrapeAnswers = QuestionManager.getSelectedScrapAnswers();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String paramName = entry.getKey();

            if (paramName.endsWith("_mark")) {
                String questionNumberStr = paramName.split("_")[0];
                String questionScrapeType = paramName.split("_")[1];

                log.info("questionNumberStr : {}", questionNumberStr);
                log.info("questionScrapeType : {}", questionScrapeType);

                //문제 숫자 인트로 만들기
                int questionNumber = Integer.parseInt(questionNumberStr);

                //알쏭달쏭이면 처리하는 로직
                if (questionScrapeType.equals("isConfusing")) {
                    selectedScrapeAnswers.put(questionNumber, new Question("Confusing", true));
                }
                //모르겠다이면 처리하는 로직
                if (questionScrapeType.equals("isUnknown")) {
                    selectedScrapeAnswers.put(questionNumber, new Question("Unknown", true));
                }

            }
        }



        log.info("CHECK SCRAPE ARRAY : {}", selectedScrapeAnswers);

        return selectedScrapeAnswers;
    }




}
