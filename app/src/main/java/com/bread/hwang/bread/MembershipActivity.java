package com.bread.hwang.bread;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MembershipActivity extends AppCompatActivity {
 /* 회원가입 화면 */
    /* 회원가입API, 아이디 중복확인API, 닉네임 중복확인API */

    /* 갤러리 연동하니까 Permission받기 */

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

        Button set = (Button) findViewById(R.id.btn_set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MembershipActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button cancel = (Button) findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MembershipActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
