package com.example.fokus;

import com.backendless.BackendlessUser;

import java.util.ArrayList;

public class Student extends Person{
    public ArrayList<Class> classArrayList = new ArrayList<Class>();

    public Student(){

    }

    public Student(String name, BackendlessUser user, String objectId, ArrayList<Class> classArrayList){
        super(name, user, objectId);
        this.classArrayList = classArrayList;
    }

    public ArrayList<Class> getClassArrayList() {
        return classArrayList;
    }

    public void setClassArrayList(ArrayList<Class> classArrayList) {
        this.classArrayList = classArrayList;
    }
}
