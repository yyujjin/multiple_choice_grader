package com.sideproject.grading.domain;


public class Question {
    //문제 번호가 필요있는지 몰라서 일단 주석 처리
    //private int number;
    //알쏭달쏭,모르겠다
    //중복 선택 불가능하니 하나로 처리
    private String scrapeType;
    private boolean isScrap;

    public Question (String scrapeType,boolean isScrap ) {
        this.scrapeType = scrapeType;
        this.isScrap = isScrap;
    }

    public String getScrapeType() {
        return scrapeType;
    }

    @Override
    public String toString() {
        return "Question{" +
                "scrapeType='" + scrapeType + '\'' +
                ", isScrap=" + isScrap +
                '}';
    }

    // public int getNumber() {
     //   return number;
    //}
}
