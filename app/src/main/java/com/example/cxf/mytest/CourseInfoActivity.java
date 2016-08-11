package com.example.cxf.mytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cxf.mytest.domain.Course;
import com.example.cxf.mytest.domain.Student;

import java.util.List;

/**
 * Created by cxf on 2016/8/8.
 * 课程信息页面，详细课程信息页面
 */
public class CourseInfoActivity extends AppCompatActivity {
    private Context context;
    private Intent intent;
    private List<Course> courseList;
    private ListView listView;
    private CousreListAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_info);

        init();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                processClick(position);
            }
        });

    }
    //初始化方法
    private void init(){

        MyApplication myApp= (MyApplication) getApplication();
        myApp.addActivity(this);

        context=this;
        intent=getIntent();
        courseList=myApp.getCourseList();
        listView= (ListView) this.findViewById(R.id.courseList);
        adapter=new CousreListAdapter(courseList,context);
        listView.setAdapter(adapter);
    }
    //处理点击的方法
    private void processClick(int index){
        String target=intent.getStringExtra("target");
        if(target==null){
            chooseCourse(index);
        }else{
            if(target.equals(getString(R.string.two))){
                return;
            }else if(target.equals(getString(R.string.four))){
                getCourseDetail(index);
            }
        }
    }
    //选课的方法
    private void chooseCourse(int index){
        Course course=courseList.get(index);
        Student stu= (Student) intent.getSerializableExtra("stu");
        if(stu!=null){
            boolean isSuccess=course.addStudent(stu);
            String result="";
            if(isSuccess){
                result="添加成功！";
                //通知适配器刷新数据
                adapter.notifyDataSetChanged();
            }else{
                result="添加失败！";
            }
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
    }
    //课程的详细信息
    private void getCourseDetail(int index){
        Intent intent=new Intent(context,QueryCourseActivity.class);
        intent.putExtra("index",index);
        startActivity(intent);
    }

}
