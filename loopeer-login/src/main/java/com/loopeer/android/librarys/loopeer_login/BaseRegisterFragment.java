package com.loopeer.android.librarys.loopeer_login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public abstract class BaseRegisterFragment extends BaseCaptchaFragment {

    private EditText mAccountEditText;
    private EditText mPasswordEditText;
    private Button mOkButton;

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
        mOkButton = (Button) getView().findViewById(R.id.btn_ok);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyInput()) {
                    requestRegister(getAccountInput(), getCaptchaInput(), getPasswordInput());
                }
            }
        });
    }

    protected abstract void requestRegister(String accountInput, String captchaInput, String passwordInput);

    protected boolean verifyInput() {
        return true;
    }

    protected String getPasswordInput() {
        return mPasswordEditText.getText().toString();
    }

    @Override
    protected EditText getAccountEditText() {
        return mAccountEditText;
    }

}
