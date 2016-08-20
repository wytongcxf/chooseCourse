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

    private Set<Activity> set=new HashSet<>();


    @Override
    public void onCreate() {
        super.onCreate();
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
