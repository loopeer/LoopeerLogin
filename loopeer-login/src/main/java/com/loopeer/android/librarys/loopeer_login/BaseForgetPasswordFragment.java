package com.loopeer.android.librarys.loopeer_login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public abstract class BaseForgetPasswordFragment extends BaseCaptchaFragment {

    EditText mAccountEditText;
    EditText mPasswordEditText;
    Button mCommitButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forget_password, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mCommitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify()) {
                    requestForgetPassword(getAccountInput(), getPasswordInput(), getCaptchaInput());
                }
            }
        });
    }

    protected boolean verify() {
        return verifyAccountInput() && verifyCaptcha() && verifyPassword();
    }

    protected boolean verifyPassword() {
        return true;
    }

    protected boolean verifyCaptcha() {
        return true;
    }

    protected abstract void requestForgetPassword(String accountInput, String passwordInput, String captchaInput);

    protected String getPasswordInput() {
        return mPasswordEditText.getText().toString();
    }

    private void initView() {
        mAccountEditText = (EditText) getView().findViewById(R.id.txt_account);
        mPasswordEditText = (EditText) getView().findViewById(R.id.txt_password);
        mCommitButton = (Button) getView().findViewById(R.id.btn_ok);
    }

    @Override
    protected EditText getAccountEditText() {
        return mAccountEditText;
    }

}
