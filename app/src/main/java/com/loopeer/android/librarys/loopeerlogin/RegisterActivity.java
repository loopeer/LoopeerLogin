package com.loopeer.android.librarys.loopeerlogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        RegisterFragment fragment = new RegisterFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_register, fragment).commit();
    }
}
