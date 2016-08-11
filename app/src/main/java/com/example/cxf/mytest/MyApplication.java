package com.example.cxf.mytest;

import android.app.Activity;
import android.app.Application;

import com.example.cxf.mytest.domain.Course;
import com.example.cxf.mytest.domain.Student;
import com.example.cxf.mytest.domain.Teacher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cxf on 2016/8/10.
 */
public class MyApplication extends Application {

    private List<Course> courseList=new ArrayList<>();

    private Set<Activity> set=new HashSet<>();

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
        Course c1=new Course(1,"java",new Teacher(1,"张三"),new ArrayList<Student>());
        Course c2=new Course(2,"mysql",new Teacher(2,"李四"),new ArrayList<Student>());
        Course c3=new Course(3,"html",new Teacher(3,"王五"),new ArrayList<Student>());
        Course c4=new Course(4,"javascript",new Teacher(4,"赵六"),new ArrayList<Student>());
        Course c5=new Course(5,"android",new Teacher(1,"赵七"),new ArrayList<Student>());
        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseList.add(c4);
        courseList.add(c5);
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void addActivity(Activity activity){
        set.add(activity);
    }

    public void exitApp(){
        for(Activity activity:set){
            if(activity!=null){
                activity.finish();
            }
        }
        System.exit(0);
    }
}
