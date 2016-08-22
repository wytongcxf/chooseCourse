package com.example.cxf.mytest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by cxf on 2016/8/8.
 * 退出系统
 */
public class ExitActivity extends BaseActivity {

    @Override
    protected SubView setSubView() {
        return new SubView() {
            @Override
            public void addSubView() {
                //设置标题
                TextView titleView = (TextView) root.findViewById(R.id.title);
                titleView.setText(getString(R.string.exit));

                View view=getLayoutInflater().inflate(R.layout.exit,null);

                //得到确定按钮
                View yesBtn= view.findViewById(R.id.yes);
                //设置事件监听，退出程序
                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myApplication.exitApp();
                    }
                });
                //得到取消按钮
                View noBtn=view.findViewById(R.id.no);
                //设置事件监听，返回主页面
                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });

                RelativeLayout relativeLayout= (RelativeLayout) root.findViewById(R.id.bottom);
                relativeLayout.addView(view);

            }
        };
    }

}
