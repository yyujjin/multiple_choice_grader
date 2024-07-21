package com.sideproject.grading.service;

import com.sideproject.grading.domain.Question;
import com.sideproject.grading.domain.QuestionManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Service
@lombok.extern.slf4j.Slf4j
public class ScrapeService {
    //이 메서드에는 이런 변수를 사용할 수 있다고 선언
    //모델로 따로 빼야하는건가?
    List<Integer> confusingList;
    List<Integer> unknownList;

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

    //알쏭달쏭이랑 모르겠다 분리하는 메서드
    public void splitScrapeType() {

        List<Integer> confusingList= new ArrayList<>();
        List<Integer> unknownList= new ArrayList<>();

        //저장 돼 있는 스크랩 문제 가져오기
        Map<Integer, Question> scrapedAnswers =QuestionManager.getSelectedScrapAnswers();
        //selectedAnswers라는 맵의 모든 엔트리(키-값 쌍)를 Set 컬렉션으로 반환합니다.
        //이 경우, 각 엔트리는 Integer 타입의 키와 SelectedAnswer 타입의 값으로 구성되어 있습니다.
        Set<Map.Entry<Integer, Question>> entries = scrapedAnswers.entrySet();
        for (Map.Entry<Integer, Question> entry : entries) {
            //알쏭달쏭 버튼
            if(entry.getValue().getScrapeType()=="isConfusing"){
                confusingList.add(entry.getKey());
                //이렇게 안하면 다른 메서드에서 인식못하니 this 해줘야함
                //안해주면 여기 스코프 안에서만 리스트 만들어짐
                this.confusingList = confusingList;
            }else{ //모르겠다 버튼
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
}
