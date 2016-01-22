package com.loopeer.android.librarys.loopeerlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.loopeer.android.librarys.loopeer_login.LoginBundleBuilder;


public class MainActivity extends AppCompatActivity {

    private CheckBox mWechatCheckBox;
    private CheckBox mWeiboCheckBox;
    private CheckBox mCaptchaCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWechatCheckBox = (CheckBox) findViewById(R.id.checkbox_wechat);
        mWeiboCheckBox = (CheckBox) findViewById(R.id.checkbox_weibo);
        mCaptchaCheckBox = (CheckBox) findViewById(R.id.checkbox_captcha);
    }

    public void openLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        LoginBundleBuilder builder = new LoginBundleBuilder();
        intent.putExtras(builder.isThirdLoginEnabled(mWechatCheckBox.isChecked() || mWeiboCheckBox.isChecked())
                .isWechatLoginEnabled(mWechatCheckBox.isChecked()).isWeiboLoginEnabled(mWeiboCheckBox.isChecked())
                .isCaptchaLoginEnabled(mCaptchaCheckBox.isChecked()).build());
        startActivity(intent);
    }

    public void openRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
