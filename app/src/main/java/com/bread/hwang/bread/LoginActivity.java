package com.bread.hwang.bread;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
/* 로그인API */
    /* SharedPreference로 유저의 id, nickname(name), password 저장하기(로그인API할때) */

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button membership = (Button) findViewById(R.id.btn_membership);
        membership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, MembershipActivity.class);
                startActivity(intent);
            }
        });
    }
}
