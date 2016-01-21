package com.loopeer.android.librarys.loopeer_login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public abstract class BaseCaptureFragment extends Fragment {

    private CountDownTimer mDownTimer;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getSendCaptureTextView() == null)
            return;
        getSendCaptureTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyAccountInput()) {
                    startCountDown();
                    requestCapture(getAccountInput());
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
        mDownTimer = new CountDownTimer(LoginConfig.CAPTURE_COUNT_DOWN, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                getSendCaptureTextView().setText(millisUntilFinished / 1000 + "s后重新发送");
            }

            @Override
            public void onFinish() {
                initSendCaptureTextView();
            }
        };
        getSendCaptureTextView().setEnabled(false);
        mDownTimer.start();
    }

    private void initSendCaptureTextView() {
        getSendCaptureTextView().setText("发送验证码");
        getSendCaptureTextView().setEnabled(true);
    }

    protected abstract TextView getSendCaptureTextView();

    protected abstract EditText getAccountEditText();

    public abstract void requestCapture(String accountInput);

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDownTimer != null)
            mDownTimer.cancel();
    }
}
