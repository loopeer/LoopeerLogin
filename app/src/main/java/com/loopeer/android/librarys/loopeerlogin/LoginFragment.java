package com.loopeer.android.librarys.loopeerlogin;

import android.content.Intent;

import com.loopeer.android.librarys.loopeer_login.BaseLoginFragment;

public class LoginFragment extends BaseLoginFragment {

    @Override
    protected void onForgetPasswordClicked() {
        Intent intent = new Intent(getActivity(), ForgetPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    protected void requestLogin(String accountInput, String passwordInput) {

    }

    @Override
    public void requestCaptcha(String accountInput) {

    }

}
