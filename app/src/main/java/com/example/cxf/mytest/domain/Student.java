package com.example.cxf.mytest.domain;

import java.io.Serializable;

/**
 * Created by cxf on 2016/8/9.
 */
public class Student implements Serializable {
    private int id;
    private String name;
    private int sex;//1:男，2：女
    private int age;
    public Student(){

    }

    public Student(String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    public Student(int id, String name, int sex, int age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }
}
