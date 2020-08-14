package com.example.fokus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.backendless.BackendlessUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Student extends Person implements Map {
    public String name;
    public BackendlessUser user;
    public String objectId;

    public ArrayList<Class> classArrayList = new ArrayList<Class>();

    public Student(){

    }
    public Student(String name, BackendlessUser user){
        super(name, user);
    }

    public Student(String name, BackendlessUser user, String objectId, String mail, ArrayList<Class> classArrayList){
        super(name, user, objectId, mail);
        this.classArrayList = classArrayList;
    }

    public ArrayList<Class> getClassArrayList() {
        return classArrayList;
    }

    public void setClassArrayList(ArrayList<Class> classArrayList) {
        this.classArrayList = classArrayList;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(@Nullable Object o) {
        return false;
    }

    @Override
    public boolean containsValue(@Nullable Object o) {
        return false;
    }

    @Nullable
    @Override
    public Object get(@Nullable Object o) {
        return null;
    }

    @Nullable
    @Override
    public Object put(Object o, Object o2) {
        return null;
    }

    @Nullable
    @Override
    public Object remove(@Nullable Object o) {
        return null;
    }

    @Override
    public void putAll(@NonNull Map map) {

    }

    @Override
    public void clear() {

    }

    @NonNull
    @Override
    public Set keySet() {
        return null;
    }

    @NonNull
    @Override
    public Collection values() {
        return null;
    }

    @NonNull
    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
