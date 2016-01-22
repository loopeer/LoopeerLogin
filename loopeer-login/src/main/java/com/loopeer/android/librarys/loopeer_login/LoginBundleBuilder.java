package com.loopeer.android.librarys.loopeer_login;

import android.os.Bundle;

public class LoginBundleBuilder {

    private LoginConfig mLoginConfig;

    public LoginBundleBuilder() {
        mLoginConfig = new LoginConfig();
    }

    public LoginBundleBuilder isThirdLoginEnabled(boolean isEnabled) {
        mLoginConfig.setThirdLoginEnabled(isEnabled);
        return this;
    }

    public LoginBundleBuilder isWechatLoginEnabled(boolean isEnabled) {
        mLoginConfig.setWechatLoginEnabled(isEnabled);
        return this;
    }

    public LoginBundleBuilder isWeiboLoginEnabled(boolean isEnabled) {
        mLoginConfig.setWeiboLoginEnabled(isEnabled);
        return this;
    }

    public LoginBundleBuilder isCaptchaLoginEnabled(boolean isEnabled) {
        mLoginConfig.setCaptchaLoginEnabled(isEnabled);
        return this;
    }

    public Bundle build() {
        return mLoginConfig.pack();
    }

}
