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

/**
 * Created by cxf on 2016/8/8.
 * 退出系统
 */
public class ExitActivity extends AppCompatActivity {
    private Button yesBtn;
    private Button noBtn;
    private Context context;
    private MyApplication myApp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exit);
        init();
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myApp.exitApp();
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,MainActivity.class));
            }
        });
    }

     private void init(){

         context=this;

         myApp=(MyApplication) getApplication();
         myApp.addActivity(this);

         yesBtn= (Button) this.findViewById(R.id.yes);
         noBtn= (Button) findViewById(R.id.no);
     }
}
