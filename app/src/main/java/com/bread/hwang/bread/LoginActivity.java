package com.bread.hwang.bread;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DisplayContext;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bread.hwang.bread.manager.PropertyManager;

import static com.bread.hwang.bread.MyApplication.context;

public class LoginActivity extends AppCompatActivity {
/* 로그인API */
    /* SharedPreference로 유저의 id, nickname(name), password 저장하기(로그인API할때) */

    Toolbar toolbar;
    TextView toolbarTitle;
    Intent intent;
    EditText idText, passwordText;
    String password;
    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        idText = (EditText) findViewById(R.id.edit_id);
        passwordText = (EditText) findViewById(R.id.edit_password);

        password = passwordText.getText().toString();

        Button login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userId = idText.getText().toString();
                PropertyManager.getInstance().setUserId(userId);
                String userPass = passwordText.getText().toString();
                PropertyManager.getInstance().setUserPassword(userPass);

                if (userPass.toString().length() < 6) {
                    Toast.makeText(LoginActivity.this, "You have to write to here more!", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(""+userId)) {
                    Toast.makeText(LoginActivity.this, "Please enter your ID", Toast.LENGTH_SHORT).show();
                }

                if (!TextUtils.isEmpty(""+userId) && userPass.toString().length() > 7) {
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mPrefs.edit();

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
