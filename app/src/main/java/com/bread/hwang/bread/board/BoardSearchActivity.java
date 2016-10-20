package com.bread.hwang.bread.board;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bread.hwang.bread.R;

public class BoardSearchActivity extends AppCompatActivity {
    /* 게시글 검색하는 액티비티(조건에 따라서) */
    /*게시글 목록(+검색API)  */
    /* Spinner의 구성이 작성자, 날짜인데 키워드가 있을경우 작성자검색/ 없으면 날짜순
    (BeautyTipFragment쪽 참고) 게시물 검색해오기 (일단 페이징 처리 ㄴㄴ, 한번에 뿌리기)*/

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_search);

        Button exit = (Button) findViewById(R.id.btn_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button detail = (Button) findViewById(R.id.btn_detail);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(BoardSearchActivity.this, BoardDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
