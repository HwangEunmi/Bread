package com.bread.hwang.bread;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MembershipActivity extends AppCompatActivity {
 /* 회원가입 화면 */
    /* 회원가입API, 아이디 중복확인API, 닉네임 중복확인API */

    /* 갤러리 연동하니까 Permission받기 */

    Intent intent;
    TextView idConfirmView, nameConfirmView;
    EditText idText, nameText, passText, passMoreText;
    ImageView profileView, cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

        idConfirmView = (TextView) findViewById(R.id.text_overlap_id);
        nameConfirmView = (TextView) findViewById(R.id.text_overlap_name);
        idText = (EditText) findViewById(R.id.edit_id);
        nameText = (EditText) findViewById(R.id.edit_name);
        passText = (EditText) findViewById(R.id.edit_password);
        passMoreText = (EditText) findViewById(R.id.edit_password_more);
        profileView = (ImageView) findViewById(R.id.image_profile);
        cameraView = (ImageView) findViewById(R.id.image_camera);


        /* cameraView 카메라 연동 (+permission) */

        /* 아이디중복확인, 닉네임중복확인(API필요함) + 후에 Dialog로 알림창 띄우기 */


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
