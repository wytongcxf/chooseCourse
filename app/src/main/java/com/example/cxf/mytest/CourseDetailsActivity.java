package com.example.cxf.mytest;

import android.content.Intent;

import com.example.cxf.mytest.domain.Course;

/**
 * Created by cxf on 2016/8/21.
 */
public class CourseDetailsActivity extends CourseInfoActivity {
    @Override
    protected void processItemClick(int position){

        Course curCousre=courseList.get(position);
        int courseId=curCousre.getId();
        Intent intent=new Intent(context,QueryCourseActivity.class);
        intent.putExtra("courseId",courseId);
        startActivity(intent);
    }
    @Override
    protected String getTitleText(){
        return getString(R.string.coursedetails);
    }
}
