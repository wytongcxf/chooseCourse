package com.example.cxf.mytest;

import android.support.percent.PercentRelativeLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by cxf on 2016/8/10.
 * 帮助
 */
public class HelpActivity extends BaseActivity {

    @Override
    protected SubView setSubView() {
        return new SubView() {
            @Override
            public void addSubView() {
                //设置标题
                TextView titleView = (TextView) root.findViewById(R.id.title);
                titleView.setText(getString(R.string.help));

                View view=getLayoutInflater().inflate(R.layout.help,null);

                RelativeLayout relativeLayout= (RelativeLayout) root.findViewById(R.id.bottom);
                relativeLayout.addView(view);

            }
        };
    }
}
