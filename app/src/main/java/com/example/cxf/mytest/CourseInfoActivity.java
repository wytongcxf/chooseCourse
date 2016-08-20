package com.example.cxf.mytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
    List<Course> courseList;
    private ListView listView;
    private BaseAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_info);

        init();
    }
    //初始化方法
    private void init(){

        MyApplication myApp= (MyApplication) getApplication();
        myApp.addActivity(this);

        context=this;
        intent=getIntent();
        listView= (ListView) this.findViewById(R.id.courseList);

        courseList=SQLiteUtil.getInstance(this,1).queryCourseForList();

        adapter= new CousreListAdapter(courseList,context);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String target=intent.getStringExtra("target");
                if(target==null){
                    chooseCourse(position);
                }else{
                    if(target.equals(getString(R.string.two))){
                        return;
                    }else if(target.equals(getString(R.string.four))){
                        getCourseDetail(position);
                    }
                }
            }
        });

    }


    //选课的方法
    private void chooseCourse(int position){
        //要显示的结果
        String result="";

        //获得当前行的课程对象
        Course curCousre=courseList.get(position);
        //获得当前行的课程id
        int cid=curCousre.getId();
        //获得当前行的课程的学生人数
        int stuCount=curCousre.getCount();

        stuCount++;

        if(stuCount>20){
            result="该课程人数已满";
        }else{
            //得到学生对象
            Student stu= (Student) intent.getSerializableExtra("stu");
            //得到学生id
            int sid=stu.getId();

            SQLiteUtil util=SQLiteUtil.getInstance(context,1);

            if(util.isChoosed(sid,cid)){
                result="你已经选了该课";
            }
            else{
                util.insertStudentCourse(sid,cid);
                result="选课成功！";
                curCousre.setCount(stuCount);
                //刷新数据
                adapter.notifyDataSetChanged();
            }
        }
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }
    //课程的详细信息
    private void getCourseDetail(int index){
        Intent intent=new Intent(context,QueryCourseActivity.class);
        intent.putExtra("index",index);
        startActivity(intent);
    }

}
