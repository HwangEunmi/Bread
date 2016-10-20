package com.bread.hwang.bread;

import android.content.Intent;
import android.icu.text.DisplayContext;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bread.hwang.bread.manager.PropertyManager;

public class LoginActivity extends AppCompatActivity {
/* 로그인API */
    /* SharedPreference로 유저의 id, nickname(name), password 저장하기(로그인API할때) */

    Intent intent;
    EditText idText, passwordText;

    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idText = (EditText) findViewById(R.id.edit_id);
        passwordText = (EditText) findViewById(R.id.edit_password);

        password = passwordText.getText().toString();

        Button login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userId = idText.getText().toString();
                PropertyManager.getInstance().setUserId(userId);
            /*SharedPreference에 저장된 값을 가져와서 지금 입력된 값이랑 비교하기 (저거 set이 아니라 get으로 가져와서 비교해야 함) */
                String userPass = passwordText.getText().toString();
                PropertyManager.getInstance().setUserPassword(userPass);

                if (userPass.toString().length() < 6) {
                    Toast.makeText(LoginActivity.this, "ssi", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(userId)) {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.hint_id), Toast.LENGTH_SHORT).show();
                }
                if (!TextUtils.isEmpty(userId) && userPass.toString().length() > 7) {
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
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
