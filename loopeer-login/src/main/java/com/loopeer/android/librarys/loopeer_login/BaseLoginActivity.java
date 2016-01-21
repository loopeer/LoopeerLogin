package com.loopeer.android.librarys.loopeer_login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BaseLoginActivity extends BaseCaptureActivity {

    LinearLayout mTopLayout;
    LinearLayout mBottomLayout;
    EditText mAccountEditText;
    EditText mPasswordEditText;
    Button mLoginButton;
    TextView mSendCaptureTextView;

    ViewStub mBottomViewStub;
    ViewStub mCaptureViewStub;

    private LoginConfig mLoginConfig = new LoginConfig();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        parseIntentToLoginConfig();
        initView();
        initInputHint();
    }

    private void parseIntentToLoginConfig() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            return;
        mLoginConfig = LoginConfig.unpack(bundle);
    }

    private void initView() {
        mTopLayout = (LinearLayout) findViewById(R.id.layout_top);
        mBottomViewStub = (ViewStub) findViewById(R.id.stub_bottom);
        mAccountEditText = (EditText) findViewById(R.id.txt_account);
        mPasswordEditText = (EditText) findViewById(R.id.txt_password);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mCaptureViewStub = (ViewStub) findViewById(R.id.stub_capture);
        initLoginView();
        initCaptureLoginView();
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyInput()) {
                    requestLogin(getAccountInput(), getPasswordInput());
                }
            }
        });
    }

    protected abstract void requestLogin(String accountInput, String passwordInput);

    private boolean verifyInput() {
        return true;
    }

    protected void initInputHint() {
        mAccountEditText.setHint("请输入手机号/邮箱");
        if (mLoginConfig.isCaptureLoginEnabled()) {
            mPasswordEditText.setHint("请输入验证码");
        } else {
            mPasswordEditText.setHint("请输入密码");
        }
    }

    private void initCaptureLoginView() {
        if (!mLoginConfig.isCaptureLoginEnabled())
            return;
        mCaptureViewStub.inflate();
        mSendCaptureTextView = (TextView) findViewById(R.id.txt_send_capture);
    }

    private String getPasswordInput() {
        return mPasswordEditText.getText().toString();
    }

    private void initLoginView() {
        if (!mLoginConfig.isThirdLoginEnabled())
            return;
        mBottomViewStub.inflate();
        mBottomLayout = (LinearLayout) findViewById(R.id.layout_bottom);
        if (mLoginConfig.isWechatLoginEnabled())
            addLoginItemView("微信", R.drawable.ic_wechat_login);
        if (mLoginConfig.isWeiboLoginEnabled())
            addLoginItemView("微博", R.drawable.ic_weibo_login);
    }

    private void addLoginItemView(String title, int resId) {
        LoginItemView loginItemView = new LoginItemView(this);
        loginItemView.setTitle(title);
        loginItemView.setIcon(resId);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        mBottomLayout.addView(loginItemView, params);
    }

    @Override
    public TextView getSendCaptureTextView() {
        return mSendCaptureTextView;
    }

    @Override
    public EditText getAccountEditText() {
        return mAccountEditText;
    }
}
