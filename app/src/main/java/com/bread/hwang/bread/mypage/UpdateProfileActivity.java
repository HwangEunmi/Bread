package com.bread.hwang.bread.mypage;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bread.hwang.bread.R;
import com.bread.hwang.bread.manager.PropertyManager;
import com.bumptech.glide.Glide;

import java.io.File;

public class UpdateProfileActivity extends AppCompatActivity {
    /* 마이 페이지 수정하는 화면 */
    /* 비밀번호 확인API, 회원수정API */

     /* 갤러리 연동하니까 Permission받기 */

    Intent intent;
    EditText userName, recentlyPassword, nowPassword, morePassword;
    ImageView userProfileImage;

    String currentPassword, newPassword, againPassword;

    private static final int RC_GET_IMAGE = 100;
    private static final int RC_PERMISSION = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        userName = (EditText) findViewById(R.id.edit_username);
        recentlyPassword = (EditText) findViewById(R.id.edit_recentlyPassword);
        nowPassword = (EditText) findViewById(R.id.edit_newPassword);
        morePassword = (EditText) findViewById(R.id.edit_morePassword);
        userProfileImage = (ImageView) findViewById(R.id.image_user_profile);

        Button camera = (Button) findViewById(R.id.btn_camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, RC_GET_IMAGE);
            }
        });

        Button set = (Button) findViewById(R.id.btn_set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewPassword();
            }
        });

        Button cancel = (Button) findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        checkPermission();
    }

    public void setNewPassword() {
        /*내 정보 수정인지 패스워드 수정인지 Intent putExtra값으로 비교 한 후 분기별로 처리 */

        currentPassword = recentlyPassword.getText().toString();
        newPassword = nowPassword.getText().toString();
        againPassword = morePassword.getText().toString();

        String password = PropertyManager.getInstance().getUserPassword();

        if(password.equals(currentPassword)) {
            if(!newPassword.equals(againPassword)) {
                Toast.makeText(UpdateProfileActivity.this, "패스워드를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
            }else {
                /* 패스워드 수정 API */

            }
        }else {
            Toast.makeText(UpdateProfileActivity.this, "패스워드가 다릅니다", Toast.LENGTH_SHORT).show();
        }
    }

    File uploadFile = null;

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

                    Glide.with(userProfileImage.getContext())
                            .load(uploadFile)
                            .into(userProfileImage);
                }
            }
        }
    }

    private void finishNoPermission() {
        Toast.makeText(this, "no permission", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_PERMISSION) {
            if (grantResults != null && grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "grant permission", Toast.LENGTH_SHORT).show();
            } else {
                finishNoPermission();
            }
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_PERMISSION);
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Runtime Permission");
                builder.setMessage("사진을 업로드하려면 앨범 접근 승인이 필요합니다.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requestPermission();
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        finishNoPermission();
                    }
                });

                builder.create().show();
                return;
            }
            requestPermission();
        }
    }
}
