package com.example.cxf.mytest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

/**
 * Created by cxf on 2016/8/21.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context context;
    protected MyApplication myApplication;
    protected RelativeLayout root;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        context=this;
        myApplication= (MyApplication) getApplication();
        myApplication.addActivity(this);
        init();
    }

    protected abstract SubView setSubView();

    protected void init(){
        root= (RelativeLayout) this.findViewById(R.id.base);
        //加载子布局
        setSubView().addSubView();
    }
}
