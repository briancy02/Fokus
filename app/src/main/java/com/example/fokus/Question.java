package com.example.fokus;

public class Question {
    String youtubeURL;
    String question;
    String answerCorrect;

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public String getQuestion() {
        return question;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }
}
