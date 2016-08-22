package com.example.cxf.mytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxf.mytest.domain.Student;

/**
 * Created by cxf on 2016/8/8.
 * 选修课程页面
 */
public class SelectCourseActivity extends BaseActivity {
    private EditText stuNameText;
    private EditText stuAgeText;
    private RadioButton boyBtn;
    private RadioButton girlBtn;
    private Button subBtn;
    private String stuName;
    private int stuSex;
    private int stuAge;

    @Override
    protected SubView setSubView() {
        return new SubView() {
            @Override
            public void addSubView() {
                //设置标题
                TextView titleView = (TextView) root.findViewById(R.id.title);
                titleView.setText(R.string.selectCourse);
                View view = getLayoutInflater().inflate(R.layout.select_course, null);

                stuNameText= (EditText)view.findViewById(R.id.stuName);
                boyBtn= (RadioButton) view.findViewById(R.id.boy);
                girlBtn= (RadioButton) view.findViewById(R.id.girl);
                stuAgeText= (EditText) view.findViewById(R.id.stuAge);
                subBtn= (Button) view.findViewById(R.id.submit);
                subBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(validateForm()){
                            Student stu=getStudent(stuName,stuSex,stuAge);
                            if(stu!=null){
                                Intent it=new Intent(context,ChooseCourseActivity.class);
                                it.putExtra("stu",stu);
                                resetForm();
                                startActivity(it);
                            }
                        }else{
                            Toast.makeText(context, "表单错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                RelativeLayout relativeLayout = (RelativeLayout) root.findViewById(R.id.bottom);
                relativeLayout.addView(view);
            }
        };
    }
    private Student getStudent(String stuName,int sex,int age){
        SQLiteUtil util= new SQLiteUtil(context,1);
        Student stu=util.queryStudent(stuName);
        if(stu!=null) {
            return stu;
        }else {
            return util.insertStudent(stuName, sex, age);
        }
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
