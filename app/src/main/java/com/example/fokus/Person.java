package com.example.fokus;

import com.backendless.BackendlessUser;

public class Person {
    public String name;
    public BackendlessUser user;
    public String objectId;
    public String mail;

    public Person(){

    }

    public Person(String name, BackendlessUser user){
        this.name = name;
        this.user = user;
    }

    public Person(String name, BackendlessUser user, String objectId, String mail){
        this.name = name;
        this.user = user;
        this.objectId = objectId;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public BackendlessUser getUser() {
        return user;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getMail() {
        return mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(BackendlessUser user) {
        this.user = user;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
