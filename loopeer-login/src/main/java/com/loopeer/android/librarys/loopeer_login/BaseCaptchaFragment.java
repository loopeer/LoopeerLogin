package com.loopeer.android.librarys.loopeer_login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.TextView;

public abstract class BaseCaptchaFragment extends Fragment {

    private CountDownTimer mDownTimer;

    private ViewStub mCaptchaViewStub;
    private TextView mSendCaptchaTextView;
    private EditText mCaptchaEditText;

    protected LoginConfig mLoginConfig = new LoginConfig();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!mLoginConfig.isCaptchaLoginEnabled())
            return;
        initCaptchaView();
        mSendCaptchaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyAccountInput()) {
                    startCountDown();
                    requestCaptcha(getAccountInput());
                }
            }
        });
    }

    protected String getAccountInput() {
        return getAccountEditText().getText().toString();
    }

    public boolean verifyAccountInput() {
        return true;
    }

    protected void startCountDown() {
        mDownTimer = new CountDownTimer(LoginConfig.CAPTCHA_COUNT_DOWN, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mSendCaptchaTextView.setText(millisUntilFinished / 1000 + "s后重新发送");
            }

            @Override
            public void onFinish() {
                initSendCaptchaTextView();
            }
        };
        mSendCaptchaTextView.setEnabled(false);
        mDownTimer.start();
    }

    private void initSendCaptchaTextView() {
        mSendCaptchaTextView.setText("发送验证码");
        mSendCaptchaTextView.setEnabled(true);
    }

    protected abstract EditText getAccountEditText();

    public abstract void requestCaptcha(String accountInput);

    private void initCaptchaView() {
        mCaptchaEditText = (EditText) getView().findViewById(R.id.txt_captcha);
        mCaptchaViewStub = (ViewStub) getView().findViewById(R.id.stub_captcha);
        mCaptchaViewStub.inflate();
        mSendCaptchaTextView = (TextView) getView().findViewById(R.id.txt_send_captcha);
    }

    protected String getCaptchaInput() {
        return mCaptchaEditText.getText().toString();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDownTimer != null)
            mDownTimer.cancel();
    }
}
