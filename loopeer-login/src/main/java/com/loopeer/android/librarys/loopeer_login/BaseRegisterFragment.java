package com.loopeer.android.librarys.loopeer_login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public abstract class BaseRegisterFragment extends BaseCaptureFragment {

    private EditText mAccountEditText;
    private EditText mPasswordEditText;
    private EditText mCaptureEditText;
    private TextView mSendCaptureTextView;
    private Button mOkButton;

    private ViewStub mCaptureViewStub;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView() {
        mAccountEditText = (EditText) getView().findViewById(R.id.txt_account);
        mPasswordEditText = (EditText) getView().findViewById(R.id.txt_password);
        mCaptureEditText = (EditText) getView().findViewById(R.id.txt_capture);
        initCaptureView();
        mOkButton = (Button) getView().findViewById(R.id.btn_ok);
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

    protected boolean verifyInput() {
        return true;
    }

    protected String getPasswordInput() {
        return mPasswordEditText.getText().toString();
    }

    protected String getCaptureInput() {
        return mCaptureEditText.getText().toString();
    }

    private void initCaptureView() {
        mCaptureViewStub = (ViewStub) getView().findViewById(R.id.stub_capture);
        mCaptureViewStub.inflate();
        mSendCaptureTextView = (TextView) getView().findViewById(R.id.txt_send_capture);
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
