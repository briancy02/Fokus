package com.example.fokus;

public class Question {
    String youtubeURL;
    String questions;
    String answerCorrect;

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public String getQuestions() {
        return questions;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }
}
