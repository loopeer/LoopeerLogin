package com.loopeer.android.librarys.loopeer_login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public abstract class BaseRegisterAcivity extends BaseCaptureActivity {

    private EditText mAccountEditText;
    private EditText mPasswordEditText;
    private EditText mCaptureEditText;
    private TextView mSendCaptureTextView;
    private Button mOkButton;

    private ViewStub mCaptureViewStub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mAccountEditText = (EditText) findViewById(R.id.txt_account);
        mPasswordEditText = (EditText) findViewById(R.id.txt_password);
        mCaptureEditText = (EditText) findViewById(R.id.txt_capture);
        initCaptureView();
        mOkButton = (Button) findViewById(R.id.btn_ok);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyInput()) {
                    requestRegister(getAccountInput(), getCaptureInput(), getPasswordInput());
                }
            }
        });
    }

    protected abstract void requestRegister(String accountInput, String captureInput, String passwordInput);

    private boolean verifyInput() {
        return true;
    }

    private String getPasswordInput() {
        return mPasswordEditText.getText().toString();
    }

    private String getCaptureInput() {
        return mCaptureEditText.getText().toString();
    }

    private void initCaptureView() {
        mCaptureViewStub = (ViewStub) findViewById(R.id.stub_capture);
        mCaptureViewStub.inflate();
        mSendCaptureTextView = (TextView) findViewById(R.id.txt_send_capture);
    }

    @Override
    protected TextView getSendCaptureTextView() {
        return mSendCaptureTextView;
    }

    @Override
    protected EditText getAccountEditText() {
        return mAccountEditText;
    }

}
