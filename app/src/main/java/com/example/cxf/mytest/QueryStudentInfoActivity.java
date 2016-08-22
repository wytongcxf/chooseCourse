package com.example.cxf.mytest;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by cxf on 2016/8/10.
 * 查询学生
 */
public class QueryStudentInfoActivity extends BaseActivity {

    private EditText stuNameText;
    private Button submitBtn;

    @Override
    protected SubView setSubView() {
        return new SubView() {
            @Override
            public void addSubView() {
                TextView titleView = (TextView) root.findViewById(R.id.title);
                titleView.setText(R.string.queryStudentInfo);
                View view = getLayoutInflater().inflate(R.layout.query_student_info, null);
                stuNameText = (EditText) view.findViewById(R.id.stuName);
                submitBtn = (Button) view.findViewById(R.id.submit);
                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        queryStudent();
                    }
                });
                RelativeLayout relativeLayout = (RelativeLayout) root.findViewById(R.id.bottom);
                relativeLayout.addView(view);
            }
        };
    }


    //重置表单数据，点击提交按钮时候调用
    private void resetForm() {
        stuNameText.setText("");
    }

    //查询学生的方法，点击提交按钮时候调用
    private void queryStudent() {
        String stuName = stuNameText.getText().toString().trim();
        if ("".equals(stuName)) {
            Toast.makeText(context, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            resetForm();
            Intent intent = new Intent(context, QueryStudentResultActivity.class);
            intent.putExtra("stuName", stuName);
            startActivity(intent);
        }
    }
}
