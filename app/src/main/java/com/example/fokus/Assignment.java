package com.example.fokus;

public class Assignment {
    String assignmentName;
    String description;
    String youtubeLink;
    Student student;
    Teacher teacher;
    String question;
    String answerCorrect;
    String answerStudent;
    String dueDate;
    Boolean complete;
    String studentEmail;
    String teacherEmail;
    String score;

    public Assignment(){
        
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Boolean getComplete() {
        return complete;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public String getAnswerStudent() {
        return answerStudent;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getQuestion() {
        return question;
    }

    public Student getStudent() {
        return student;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public void setAnswerStudent(String answerStudent) {
        this.answerStudent = answerStudent;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
