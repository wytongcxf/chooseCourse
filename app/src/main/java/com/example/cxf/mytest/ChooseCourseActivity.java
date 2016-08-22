package com.example.cxf.mytest;

import android.widget.Toast;

import com.example.cxf.mytest.domain.Course;
import com.example.cxf.mytest.domain.Student;

/**
 * Created by cxf on 2016/8/21.
 */
public class ChooseCourseActivity extends CourseInfoActivity {
    @Override
    protected void processItemClick(int position) {
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
            Student stu= (Student) getIntent().getSerializableExtra("stu");
            //得到学生id
            int sid=stu.getId();

            SQLiteUtil util=new SQLiteUtil(context,1);

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
}
