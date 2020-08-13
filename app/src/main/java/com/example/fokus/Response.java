package com.example.fokus;

public class Response {
    String question;
    String answerCorrect;
    String answerStudent;

    public Response(){

    }

    public Response(String question, String answerCorrect){
        this.question = question;
        this.answerCorrect = answerCorrect;
    }

    public Response(String question, String answerCorrect, String answerStudent){
        this.question = question;
        this.answerCorrect = answerCorrect;
        this.answerStudent = answerStudent;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswerStudent() {
        return answerStudent;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswerStudent(String answerStudent) {
        this.answerStudent = answerStudent;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }
}
