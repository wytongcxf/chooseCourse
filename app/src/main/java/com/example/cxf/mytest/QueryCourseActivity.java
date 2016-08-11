package com.example.cxf.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.cxf.mytest.domain.Course;

import java.util.List;

/**
 * Created by cxf on 2016/8/10.
 * 查询课程结果
 */
public class QueryCourseActivity extends AppCompatActivity {
    private TextView title;
    private TextView queryCourseText;
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_result);
        init();
    }
    private void init(){

        MyApplication myApp=(MyApplication) getApplication();
        myApp.addActivity(this);

        title= (TextView) this.findViewById(R.id.title);
        title.setText(getString(R.string.queryCourse));

        queryCourseText= (TextView) this.findViewById(R.id.queryResult);
        intent=getIntent();
        int courseIndex=intent.getIntExtra("index",0);
        List<Course> courseList=myApp.getCourseList();
        Course course=courseList.get(courseIndex);
        int stuCount=course.getStuList().size();
        String result=course.getName()+" 已有"+stuCount+"人选，还可以增加 "+(20-stuCount)+" 人";
        queryCourseText.setText(result);
    }
}
