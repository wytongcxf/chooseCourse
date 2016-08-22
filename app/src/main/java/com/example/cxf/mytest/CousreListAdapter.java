package com.example.cxf.mytest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cxf.mytest.domain.Course;

import java.util.List;

/**
 * Created by cxf on 2016/8/9.
 */
public class CousreListAdapter extends BaseAdapter {

    List<Course> courseList;
    LayoutInflater layoutInflater;
    public CousreListAdapter(List<Course> courseList, Context context){
        this.courseList=courseList;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return courseList==null?0:courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null){
            vh=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.course_list,parent,false);
            vh.t1= (TextView) convertView.findViewById(R.id.courseId);
            vh.t2= (TextView) convertView.findViewById(R.id.courseName);
            vh.t3= (TextView) convertView.findViewById(R.id.teacherName);
            vh.t4= (TextView) convertView.findViewById(R.id.studentCount);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolder) convertView.getTag();
        }

        Course course=courseList.get(position);
        vh.t1.setText(String.valueOf(course.getId()));
        vh.t2.setText(course.getName());
        vh.t3.setText(course.getTeacher().getName());
        vh.t4.setText(String.valueOf(course.getCount()));

        return convertView;
    }
}
class ViewHolder{
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
}