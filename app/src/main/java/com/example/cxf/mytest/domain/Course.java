package com.example.cxf.mytest.domain;

import java.util.List;

/**
 * Created by cxf on 2016/8/9.
 */
public class Course {
    private int id;
    private String name;
    private Teacher teacher;
    private List<Student> stuList;

    public Course(){

    }

    public Course(int id, String name, Teacher teacher, List<Student> stuList) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.stuList = stuList;
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

    public List<Student> getStuList() {
        return stuList;
    }

    public void setStuList(List<Student> stuList) {
        this.stuList = stuList;
    }

    public boolean addStudent(Student stu){
        if(stu==null){
            return false;
        }
        if(stuList.size()>=20){
            return false;
        }
        for(Student s:stuList){
            if(s.getName().equals(stu.getName())){
                return false;
            }
        }
        stuList.add(stu);
        return true;
    }
}
