package com.loopeer.android.librarys.loopeer_login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BaseLoginFragment extends BaseCaptureFragment {

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntentToLoginConfig();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
        super.onViewCreated(view, savedInstanceState);
        initInputHint();
    }

    private void parseIntentToLoginConfig() {
        Bundle bundle = getArguments();
        if (bundle == null)
            return;
        mLoginConfig = LoginConfig.unpack(bundle);
    }

    private void initView() {
        mTopLayout = (LinearLayout) getView().findViewById(R.id.layout_top);
        mBottomViewStub = (ViewStub) getView().findViewById(R.id.stub_bottom);
        mAccountEditText = (EditText) getView().findViewById(R.id.txt_account);
        mPasswordEditText = (EditText) getView().findViewById(R.id.txt_password);
        mLoginButton = (Button) getView().findViewById(R.id.btn_login);
        mCaptureViewStub = (ViewStub) getView().findViewById(R.id.stub_capture);
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

    protected boolean verifyInput() {
        return verifyAccountInput() && verifyPassworkInput();
    }

    protected boolean verifyPassworkInput() {
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
        mSendCaptureTextView = (TextView) getView().findViewById(R.id.txt_send_capture);
    }

    protected String getPasswordInput() {
        return mPasswordEditText.getText().toString();
    }

    private void initLoginView() {
        if (!mLoginConfig.isThirdLoginEnabled())
            return;
        mBottomViewStub.inflate();
        mBottomLayout = (LinearLayout) getView().findViewById(R.id.layout_bottom);
        if (mLoginConfig.isWechatLoginEnabled())
            addLoginItemView("微信", R.drawable.ic_wechat_login);
        if (mLoginConfig.isWeiboLoginEnabled())
            addLoginItemView("微博", R.drawable.ic_weibo_login);
    }

    private void addLoginItemView(String title, int resId) {
        LoginItemView loginItemView = new LoginItemView(getActivity());
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
