package com.loopeer.android.librarys.loopeer_login;

import android.os.Bundle;

import java.util.Set;

public class LoginConfig {

    public static final long CAPTCHA_COUNT_DOWN = 60000;

    public static final String THIRD_LOGIN_FLAG = "third_login_flag";
    public static final String WECHAT_LOGIN_FLAG = "wechat_login_flag";
    public static final String WEIBO_LOGIN_FLAG = "weibo_login_flag";
    public static final String CAPTCHA_LOGIN_FLAG = "captcha_login_flag";

    private boolean mIsThirdLoginEnabled = true;
    private boolean mIsWechatLoginEnabled = true;
    private boolean mIsWeiboLoginEnabled = true;
    private boolean mIsCaptchaLoginEnabled = true;

    public Bundle pack() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(THIRD_LOGIN_FLAG, mIsThirdLoginEnabled);
        bundle.putBoolean(WECHAT_LOGIN_FLAG, mIsWechatLoginEnabled);
        bundle.putBoolean(WEIBO_LOGIN_FLAG, mIsWeiboLoginEnabled);
        bundle.putBoolean(CAPTCHA_LOGIN_FLAG, mIsCaptchaLoginEnabled);
        return bundle;
    }

    public static LoginConfig unpack(Bundle bundle) {
        LoginConfig loginConfig = new LoginConfig();
        Set<String> keys = bundle.keySet();
        if (keys.contains(THIRD_LOGIN_FLAG)) {
            loginConfig.setThirdLoginEnabled(bundle.getBoolean(THIRD_LOGIN_FLAG));
        }
        if (keys.contains(WECHAT_LOGIN_FLAG)) {
            loginConfig.setWechatLoginEnabled(bundle.getBoolean(WECHAT_LOGIN_FLAG));
        }
        if (keys.contains(WEIBO_LOGIN_FLAG)) {
            loginConfig.setWeiboLoginEnabled(bundle.getBoolean(WEIBO_LOGIN_FLAG));
        }
        if (keys.contains(CAPTCHA_LOGIN_FLAG)) {
            loginConfig.setCaptchaLoginEnabled(bundle.getBoolean(CAPTCHA_LOGIN_FLAG));
        }
        return loginConfig;
    }

    public boolean isThirdLoginEnabled() {
        return mIsThirdLoginEnabled;
    }

    public boolean isWechatLoginEnabled() {
        return mIsWechatLoginEnabled;
    }

    public boolean isWeiboLoginEnabled() {
        return mIsWeiboLoginEnabled;
    }

    public boolean isCaptchaLoginEnabled() {
        return mIsCaptchaLoginEnabled;
    }

    public void setThirdLoginEnabled(boolean thirdLoginEnabled) {
        mIsThirdLoginEnabled = thirdLoginEnabled;
    }

    public void setWechatLoginEnabled(boolean wechatLoginEnabled) {
        mIsWechatLoginEnabled = wechatLoginEnabled;
    }

    public void setWeiboLoginEnabled(boolean weiboLoginEnabled) {
        mIsWeiboLoginEnabled = weiboLoginEnabled;
    }

    public void setCaptchaLoginEnabled(boolean captchaLoginEnabled) {
        mIsCaptchaLoginEnabled = captchaLoginEnabled;
    }
}
