package com.example.cxf.mytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.cxf.mytest.domain.Student;

/**
 * Created by cxf on 2016/8/8.
 * 选修课程页面
 */
public class SelectCourseActivity extends AppCompatActivity {
    private Context context;
    private EditText stuNameText;
    private EditText stuAgeText;
    private RadioButton boyBtn;
    private RadioButton girlBtn;
    private String stuName;
    private int stuSex;
    private int stuAge;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_course);

        context=this;
        init();

        this.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(validateForm()){
                       Student stu= SQLiteUtil.getInstance(context,1).queryStudent(stuName,stuSex,stuAge);
                        if(stu!=null){
                            Intent it=new Intent(context,CourseInfoActivity.class);
                            it.putExtra("stu",stu);
                            resetForm();
                            startActivity(it);
                        }
                    }else{
                        Toast.makeText(context, "表单错误", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        this.findViewById(R.id.returnHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,MainActivity.class));
            }
        });

    }

    //初始化方法
    private void init(){

        MyApplication myApp=(MyApplication) getApplication();
        myApp.addActivity(this);
        stuNameText= (EditText) findViewById(R.id.stuName);
        boyBtn= (RadioButton) findViewById(R.id.boy);
        girlBtn= (RadioButton) findViewById(R.id.girl);
        stuAgeText= (EditText) findViewById(R.id.stuAge);

    }
    //验证表单的方法
    private boolean validateForm(){

        String name=stuNameText.getText().toString().trim();
        if("".equals(name)){
            Toast.makeText(context, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            stuName=name;
        }

        if(boyBtn.isChecked()){
            stuSex=1;
        }else if(girlBtn.isChecked()){
            stuSex=2;
        }

        String age=stuAgeText.getText().toString().trim();
        if("".equals(age)){
            Toast.makeText(context, "年龄不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            try {
                stuAge=Integer.parseInt(age);
            }catch (NumberFormatException e){
                Toast.makeText(context, "年龄必须为数字", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
    //重置表单的方法
    private void resetForm(){
        stuNameText.setText("");
        boyBtn.setChecked(true);
        girlBtn.setChecked(false);
        stuAgeText.setText("");
    }

}
