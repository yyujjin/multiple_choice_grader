package com.sideproject.grading.service;

import com.sideproject.grading.domain.SelectedAnswer;
import com.sideproject.grading.domain.SelectedAnswerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AnswerSelectionService {
    private static final Logger log = LoggerFactory.getLogger(AnswerSelectionService.class);
    int page;
    String pageType;
    List<Integer> questions;

    @Autowired
    public AnswerSelectionService() {
    }

    public void setQuestions(int totalCount) {
        List<Integer> questions = new ArrayList<>();
        for (int i = 0; i < totalCount; i++) {
            questions.add(i+1);
        }
        this.questions = questions;
    }

    public void setPage(String page) {
        this.page = Integer.parseInt(page);
    }

    public int getPage() {
        return page;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public Map<Integer, SelectedAnswer> getSelectedAnswers(Map<String, String> parameters) {
        Map<Integer, SelectedAnswer> selectedAnswers = SelectedAnswerManager.getSelectedAnswers();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String paramName = entry.getKey();
            if (paramName.endsWith("_answer")) {
                String questionNumberStr = paramName.split("_")[0];
                int questionNumber = Integer.parseInt(questionNumberStr);
                int answerNumber = Integer.parseInt(entry.getValue());

                selectedAnswers.put(questionNumber, new SelectedAnswer(questionNumber, answerNumber));
            }
        }

        return selectedAnswers;
    }

    public int getNextPage() {
        if (pageType.equals("prev")) {
            return --page;
        }

        return ++page;
    }

    public boolean hasNext(int page, int total, int limit) {
        return page < Math.ceil((double) total / limit);
    }

    public List<Integer> sliceAnswers(int page, int limitCount) {
        int end = page * limitCount;
        if (end > questions.size()) {
            end = questions.size();
        }
        return questions.subList((page - 1) * limitCount, end);
    }


    //스크랩 파라미터 거르는거 만들기
    public Map<Integer, SelectedAnswer> getScrapedAnswers(Map<String, String> parameters) {
        //이거Question으로 바꾸기
        Map<Integer, SelectedAnswer> selectedAnswers = SelectedAnswerManager.getSelectedAnswers();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String paramName = entry.getKey();
            //이거로 끝나는 파라미터 있으면
            //0,1, 나누기
            if (paramName.endsWith("_mark")) {
                String questionNumberStr = paramName.split("_")[0];
                String questionScrapeType = paramName.split("_")[1];

                log.info("questionNumberStr : {}",questionNumberStr);
                log.info("questionScrapeType : {}", questionScrapeType);

//                //문제 숫자 인트로 만들기
//                int questionNumber = Integer.parseInt(questionNumberStr);
//                //불리언으로 만들기
//                int answerNumber = Integer.parseInt(entry.getValue());
//
//                selectedAnswers.put(questionNumber, new SelectedAnswer(questionNumber, answerNumber));
            }
        }

        return selectedAnswers;
    }

}
