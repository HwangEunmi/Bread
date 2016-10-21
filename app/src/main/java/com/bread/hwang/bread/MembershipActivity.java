package com.bread.hwang.bread;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;


public class MembershipActivity extends AppCompatActivity {
 /* 회원가입 화면 */
    /* 회원가입API, 아이디 중복확인API, 닉네임 중복확인API */

    /* 갤러리 연동하니까 Permission받기 */

    Intent intent;
    TextView idConfirmView, nameConfirmView;
    EditText idText, nameText, passText, passConfirmText;
    ImageView profileView;

    File uploadFile = null;

    private static final int RC_GET_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

        idConfirmView = (TextView) findViewById(R.id.text_overlap_id);
        nameConfirmView = (TextView) findViewById(R.id.text_overlap_name);
        idText = (EditText) findViewById(R.id.edit_id);
        nameText = (EditText) findViewById(R.id.edit_name);
        passText = (EditText) findViewById(R.id.edit_password);
        passConfirmText = (EditText) findViewById(R.id.edit_pass_confirm);
        profileView = (ImageView) findViewById(R.id.image_profile);

        Button camera = (Button) findViewById(R.id.btn_camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, RC_GET_IMAGE);
            }
        });
        /* 아이디중복확인, 닉네임중복확인(API필요함) + 후에 Dialog로 알림창 띄우기 */


        Button set = (Button) findViewById(R.id.btn_set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intent = new Intent(MembershipActivity.this, LoginActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        Button cancel = (Button) findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intent = new Intent(MembershipActivity.this, LoginActivity.class);
//                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GET_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Cursor c = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);

                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    uploadFile = new File(path);

                    Glide.with(this)
                            .load(uploadFile)
                            .into(profileView);
                }
            }
        }

    }


}
