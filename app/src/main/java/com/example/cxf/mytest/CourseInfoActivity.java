package com.example.cxf.mytest;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cxf.mytest.domain.Course;

import java.util.List;

/**
 * Created by cxf on 2016/8/8.
 * 课程信息页面
 */
public class CourseInfoActivity extends BaseActivity {
    protected List<Course> courseList;
    protected ListView listView;
    protected BaseAdapter adapter;
    @Override
    protected SubView setSubView() {
        return new SubView() {
            @Override
            public void addSubView() {
                //设置标题
                TextView titleView = (TextView) root.findViewById(R.id.title);
                titleView.setText(getTitleText());

                View view = getLayoutInflater().inflate(R.layout.coursedetails, null);

                courseList = (new SQLiteUtil(context,1)).queryCourseForList();

                listView = (ListView) view.findViewById(R.id.courseList);
                adapter = new CousreListAdapter(courseList, context);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        processItemClick(position);

                    }
                });

                RelativeLayout relativeLayout = (RelativeLayout) root.findViewById(R.id.bottom);
                relativeLayout.addView(view);
            }
        };
    }

    protected String getTitleText(){
        return getString(R.string.queryStudentInfo);
    }
    protected void processItemClick(int position) {
        return;
    }
}
