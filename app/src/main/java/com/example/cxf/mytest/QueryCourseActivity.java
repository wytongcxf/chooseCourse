package com.example.cxf.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cxf.mytest.domain.Course;

import java.util.List;

/**
 * Created by cxf on 2016/8/10.
 * 查询课程结果
 */
public class QueryCourseActivity extends BaseActivity {


    @Override
    protected SubView setSubView() {
        return new SubView() {
            @Override
            public void addSubView() {
                TextView titleView = (TextView) root.findViewById(R.id.title);
                titleView.setText(R.string.queryCoursedetails);
                View view = getLayoutInflater().inflate(R.layout.query_result, null);

                TextView textView= (TextView) view.findViewById(R.id.result);
                textView.setText(getQueryResult());

                RelativeLayout relativeLayout = (RelativeLayout) root.findViewById(R.id.bottom);
                relativeLayout.addView(view);
            }
        };
    }
    private String getQueryResult(){
        Intent intent=getIntent();
        int courseId=intent.getIntExtra("courseId",0);
        Course course=(new SQLiteUtil(this,1)).queryCourse(courseId);
        int stuCount=course.getCount();
        return course.getName()+" 已有"+stuCount+"人选，还可以增加 "+(20-stuCount)+" 人";
    }
}
