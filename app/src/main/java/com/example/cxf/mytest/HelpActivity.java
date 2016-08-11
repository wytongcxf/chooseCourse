package com.example.cxf.mytest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by cxf on 2016/8/10.
 * 帮助
 */
public class HelpActivity extends AppCompatActivity {
    private TextView title;
    private TextView centerText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_result);
        init();
    }
    private void init(){

        MyApplication myApp=(MyApplication) getApplication();
        myApp.addActivity(this);

        title= (TextView) this.findViewById(R.id.title);
        title.setText(getString(R.string.five));
        centerText= (TextView) this.findViewById(R.id.queryResult);
        String helpInfo="这是帮助信息！";
        centerText.setText(helpInfo);
    }
}
