package com.sideproject.grading.domain;


public class Question {

    private ScrapeType scrapeType;

    public Question (ScrapeType scrapeType) {
        this.scrapeType = scrapeType;
    }

    public ScrapeType getScrapeType() {
        return scrapeType;
    }
}
