package com.loopeer.android.librarys.loopeerlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.loopeer.android.librarys.loopeer_login.LoginConfig;


public class MainActivity extends AppCompatActivity {

    private CheckBox mWechatCheckBox;
    private CheckBox mWeiboCheckBox;
    private CheckBox mCaptureCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWechatCheckBox = (CheckBox) findViewById(R.id.checkbox_wechat);
        mWeiboCheckBox = (CheckBox) findViewById(R.id.checkbox_weibo);
        mCaptureCheckBox = (CheckBox) findViewById(R.id.checkbox_capture);
    }

    public void openLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(LoginConfig.THIRD_LOGIN_FLAG, mWechatCheckBox.isChecked() || mWeiboCheckBox.isChecked());
        bundle.putBoolean(LoginConfig.WECHAT_LOGIN_FLAG, mWechatCheckBox.isChecked());
        bundle.putBoolean(LoginConfig.WEIBO_LOGIN_FLAG, mWeiboCheckBox.isChecked());
        bundle.putBoolean(LoginConfig.CAPTURE_LOGIN_FLAG, mCaptureCheckBox.isChecked());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void openRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
