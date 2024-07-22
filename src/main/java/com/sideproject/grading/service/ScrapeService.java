package com.sideproject.grading.service;

import com.sideproject.grading.domain.Question;
import com.sideproject.grading.domain.QuestionManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ScrapeService {
    List<Integer> confusingList;
    List<Integer> unknownList;

    public Map<Integer, Question> getScrapedAnswers(Map<String, String> parameters) {

        Map<Integer, Question> selectedScrapeAnswers = QuestionManager.getSelectedScrapAnswers();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String paramName = entry.getKey();

            if (paramName.endsWith("_mark")) {
                String questionNumberStr = paramName.split("_")[0];
                String questionScrapeType = paramName.split("_")[1];

                int questionNumber = Integer.parseInt(questionNumberStr);

                //현재는 중복 설정안되게 버튼 하나의 값만 넘어오지만
                //추후에 버튼 중복 설정 가능하도록 설정 변경 시 bool값을 하기위해 t/f값 넣는 코드로 작성

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
            if (entry.getValue().getScrapeType().equals("Confusing") && entry.getValue().getIsScrap()) {
                confusingList.add(entry.getKey());
                this.confusingList = confusingList;
            }
            //모르겠다 버튼
            if (entry.getValue().getScrapeType().equals("Unknown") && entry.getValue().getIsScrap()) {
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
