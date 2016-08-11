package com.example.cxf.mytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private Map<Integer,Class> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        context=this;

        MyApplication myApp=(MyApplication) getApplication();
        myApp.addActivity(this);

        map=new HashMap<>();
        map.put(R.id.text1,SelectCourseActivity.class);
        map.put(R.id.text2,CourseInfoActivity.class);
        map.put(R.id.text3,QueryStudentInfoActivity.class);
        map.put(R.id.text4,CourseInfoActivity.class);
        map.put(R.id.text5,HelpActivity.class);
        map.put(R.id.text6,ExitActivity.class);

        Set<Integer> set=map.keySet();

        for(final Integer integer:set){
            View view=this.findViewById(integer);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String target=((TextView)v).getText().toString();
                    Intent intent=new Intent(context,map.get(integer));
                    intent.putExtra("target",target);
                    startActivity(intent);
                }
            });
        }
    }

}
