package com.bread.hwang.bread.board;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bread.hwang.bread.R;

public class BoardDetailActivity extends AppCompatActivity {
 /* 게시글 상세페이지 */
    /* 게시글 상세조회API, 게시물 삭제API, 파일삭제API */

    /* 게시물 상세페이지에서 댓글버튼 누르면 뜨는 댓글창 */
    /* 댓글 등록API, 댓글 목록API, 댓글 수정API, 댓글 삭제API*/

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        Button back = (Button) findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        Button update = (Button) findViewById(R.id.btn_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(BoardDetailActivity.this, BoardWriteActivity.class);
                startActivity(intent);
            }
        });

    }
}
