package com.example.cxf.mytest.domain;

import java.util.List;

/**
 * Created by cxf on 2016/8/9.
 */
public class Course {
    private int id;
    private String name;
    private Teacher teacher;
    private int count;

    public Course(){

    }

    public Course(int id, String name, Teacher teacher, int count) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.count=count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
