package com.bread.hwang.bread.board;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bread.hwang.bread.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

public class BoardWriteActivity extends AppCompatActivity {
  /* 게시물 작성(등록), 수정 API*/
    /* 게시물 등록API, 게시물 수정API*/

    Toolbar toolbar;
    TextView toolbarTitle;

    Intent intent;
    ImageView imageOne, imageTwo, imageThree, userProfile;
    TextView content, userName;

    private static final int RC_GET_ONE_IMAGE = 100;
    private static final int RC_GET_TWO_IMAGE = 200;
    private static final int RC_GET_THREE_IMAGE = 300;
    private static final int RC_PERMISSION = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.text_toolbar_title);
        toolbarTitle.setText("게시물 작성 화면");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        imageOne = (ImageView) findViewById(R.id.image_one);
        imageTwo = (ImageView) findViewById(R.id.image_two);
        imageThree = (ImageView) findViewById(R.id.image_three);
        userProfile = (ImageView) findViewById(R.id.image_user_profile);
        content = (TextView) findViewById(R.id.text_content);
        userName = (TextView) findViewById(R.id.text_username);


        if (savedInstanceState != null) {
            String onePath = savedInstanceState.getString("onefile");
            String twoPath = savedInstanceState.getString("twofile");
            String threePath = savedInstanceState.getString("threefile");
            if (!TextUtils.isEmpty(onePath)) {
                uploadOneFile = new File(onePath);
            } else if (!TextUtils.isEmpty(threePath)) {
                uploadTwoFile = new File(twoPath);
            } else {
                uploadThreeFile = new File(threePath);
            }

        }

        imageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, RC_GET_ONE_IMAGE);
            }
        });

        imageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, RC_GET_TWO_IMAGE);
            }
        });

        imageThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, RC_GET_THREE_IMAGE);
            }
        });


        Button set = (Button) findViewById(R.id.btn_set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


    File uploadOneFile, uploadTwoFile, uploadThreeFile = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GET_ONE_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Cursor c = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);

                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    uploadOneFile = new File(path);

                    Glide.with(imageOne.getContext())
                            .load(uploadOneFile)
                            .into(imageOne);
                }
            }
        } else if (requestCode == RC_GET_TWO_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Cursor c = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);

                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    uploadTwoFile = new File(path);

                    Glide.with(imageTwo.getContext())
                            .load(uploadTwoFile)
                            .into(imageTwo);
                }
            }
        } else {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Cursor c = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);

                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    uploadThreeFile = new File(path);

                    Glide.with(imageThree.getContext())
                            .load(uploadThreeFile)
                            .into(imageThree);
                }
            }
        }

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

    private void finishNoPermission() {
        Toast.makeText(this, "no permission", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_PERMISSION);
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

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (uploadOneFile != null) {
            outState.putString("onefile", uploadOneFile.getAbsolutePath());
        } else if (uploadTwoFile != null) {
            outState.putString("twofile", uploadTwoFile.getAbsolutePath());
        } else {
            outState.putString("threefile", uploadThreeFile.getAbsolutePath());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_cancel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_cancel) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
