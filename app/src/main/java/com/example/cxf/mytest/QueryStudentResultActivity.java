package com.example.cxf.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.cxf.mytest.domain.Course;
import com.example.cxf.mytest.domain.Student;

import java.util.List;

/**
 * Created by cxf on 2016/8/10.
 * 查询学生结果
 */
public class QueryStudentResultActivity extends AppCompatActivity {
    private TextView title;
    private TextView queryResultText;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_result);
        init();
    }

    private void init(){
        title= (TextView) this.findViewById(R.id.title);
        title.setText(getString(R.string.queryResult));

        MyApplication myApp=(MyApplication) getApplication();
        myApp.addActivity(this);

        List<Course> courseList=myApp.getCourseList();
        intent=getIntent();
        String stuName=intent.getStringExtra("stuName");
        String result=stuName+"选的课程为：";
        for(Course course:courseList){
            List<Student> students=course.getStuList();
            for(Student student:students){
                if(stuName.equals(student.getName())){
                    result+=" "+course.getName();
                }
            }
        }
        queryResultText= (TextView) this.findViewById(R.id.queryResult);
        queryResultText.setText(result);
    }

}