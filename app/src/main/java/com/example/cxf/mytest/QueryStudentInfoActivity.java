package com.example.cxf.mytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by cxf on 2016/8/10.
 * 查询学生
 */
public class QueryStudentInfoActivity extends AppCompatActivity {
    private Context context;
    private EditText stuNameText;
    private Button submitBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_student_info);
        init();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryStudent();
            }
        });
    }

    private void init(){
        MyApplication myApp=(MyApplication) getApplication();
        myApp.addActivity(this);

        context=this;
        stuNameText= (EditText) this.findViewById(R.id.stuName);
        submitBtn= (Button) this.findViewById(R.id.submit);
    }
    //重置表单数据，点击提交按钮时候调用
    private void resetForm(){
        stuNameText.setText("");
    }
    //查询学生的方法，点击提交按钮时候调用
    private void queryStudent(){
        String stuName=stuNameText.getText().toString().trim();
        if("".equals(stuName)){
            Toast.makeText(context, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else{
            resetForm();
            Intent intent=new Intent(context,QueryStudentResultActivity.class);
            intent.putExtra("stuName",stuName);
            startActivity(intent);
        }
    }
}
