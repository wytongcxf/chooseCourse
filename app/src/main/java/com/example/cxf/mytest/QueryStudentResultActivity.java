package com.example.cxf.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cxf.mytest.domain.Course;
import com.example.cxf.mytest.domain.Student;

import java.util.List;

/**
 * Created by cxf on 2016/8/10.
 * 查询学生结果
 */
public class QueryStudentResultActivity extends BaseActivity {

    @Override
    protected SubView setSubView() {
        return new SubView() {
            @Override
            public void addSubView() {
                TextView titleView = (TextView) root.findViewById(R.id.title);
                titleView.setText(R.string.queryStudentInfo);
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
        String stuName=intent.getStringExtra("stuName");
        List<String> list=(new SQLiteUtil(this,1)).getCourseInfo(stuName);
        String result=stuName+"选的课程为：";
        for(String s:list){
            result+=s+" ";
        }
        return result;
    }

}
