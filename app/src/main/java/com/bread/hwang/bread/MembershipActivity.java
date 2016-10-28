package com.bread.hwang.bread;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bread.hwang.bread.manager.PropertyManager;
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
    AlertDialog dialog;

    private static final int RC_GET_IMAGE = 1;
    private static final int RC_PERMISSION = 500;

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

        idConfirmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOverlapId();
            }
        });

        nameConfirmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOverlapName();
            }
        });

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
                setNotMatchPassword(v);

                finish();
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

    public void setNotMatchPassword(View view) {
        String onePassword = passText.getText().toString();
        String twoPassword = passConfirmText.getText().toString();

        if(onePassword != twoPassword) {
            Snackbar.make(view, "Please enter your ID", Snackbar.LENGTH_SHORT).setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            passText.setText("");
                            passConfirmText.setText("");
                        }
                    }).show();
        }
    }

    public void onOverlapId() {
        final String id = idText.getText().toString();
        /* 아이디중복 API */
        AlertDialog.Builder builder = new AlertDialog.Builder(MembershipActivity.this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Confirm ID");
        builder.setMessage(" Overlap ID");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                idText.setText("");
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    public void onOverlapName() {
        String name = nameText.getText().toString();
        /* 이름중복 API */
        AlertDialog.Builder builder = new AlertDialog.Builder(MembershipActivity.this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Confirm Name");
        builder.setMessage(" Overlap Name");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nameText.setText("");
            }
        });

        dialog = builder.create();
        dialog.show();
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
