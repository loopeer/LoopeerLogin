package com.loopeer.android.librarys.loopeer_login;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginItemView extends FrameLayout {

    private LinearLayout mLoginItemContainerLinearLayout;
    private ImageView mIconImageView;
    private TextView mTitleTextView;

    public LoginItemView(Context context) {
        this(context, null);
    }

    public LoginItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_login_item, this, true);
        mLoginItemContainerLinearLayout = (LinearLayout) findViewById(R.id.layout_login_item_container);
        mIconImageView = (ImageView) findViewById(R.id.img_icon);
        mTitleTextView = (TextView) findViewById(R.id.txt_title);
    }

    public void setTitle(String title) {
        mTitleTextView.setText(title);
    }

    public void setIcon(int resId) {
        mIconImageView.setImageResource(resId);
    }

}
