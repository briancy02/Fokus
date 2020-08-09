package com.example.fokus;

import java.util.ArrayList;

public class Class {
    public ArrayList<Student> studentArrayList = new ArrayList<Student>();
    public Teacher teacher;

    public Class(){

    }

    public Class(ArrayList<Student> studentArrayList, Teacher teacher){
        this.studentArrayList = studentArrayList;
        this.teacher = teacher;
    }

    public ArrayList<Student> getStudentArrayList() {
        return studentArrayList;
    }

    public void setStudentArrayList(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
