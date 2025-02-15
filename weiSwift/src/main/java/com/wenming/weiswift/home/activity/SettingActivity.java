package com.wenming.weiswift.home.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wenming.weiswift.R;
import com.wenming.weiswift.common.AccessTokenKeeper;
import com.wenming.weiswift.home.util.ActivityCollector;
import com.wenming.weiswift.home.util.NewFeature;

/**
 * Created by wenmingvs on 2016/1/7.
 */
public class SettingActivity extends Activity {

    private Context mContext;
    private RelativeLayout mExitLayout;
    private ImageView mBackImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.settings_layout);
        ActivityCollector.addActivity(this);
        mContext = this;
        initToolBar();
        initContent();

    }

    private void initContent() {
        mExitLayout = (RelativeLayout) findViewById(R.id.exitLayout);
        mBackImageView = (ImageView) findViewById(R.id.backarrow);
        mExitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("确定要注销并且退出微博？")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AccessTokenKeeper.clear(getApplicationContext());
                                NewFeature.LOGIN_STATUS = false;
                                ActivityCollector.finishAll();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initToolBar() {
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.toolbar_settings);
    }
}
