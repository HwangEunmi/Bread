package com.bread.hwang.bread.board;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bread.hwang.bread.R;

public class BoardWriteActivity extends AppCompatActivity {
  /* 게시물 작성(등록), 수정 API*/
    /* 게시물 등록API, 게시물 수정API*/

    /* 갤러리 연동하니까 Permission받기 */
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

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
    }
}
