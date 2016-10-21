package com.bread.hwang.bread.board;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bread.hwang.bread.MainActivity;
import com.bread.hwang.bread.R;
import com.bread.hwang.bread.adapter.BoardDetailReplyAdapter;
import com.bread.hwang.bread.data.Reply;
import com.bread.hwang.bread.data.User;
import com.bread.hwang.bread.view.BoardDetailReplyViewHolder;

public class BoardDetailActivity extends AppCompatActivity {
 /* 게시글 상세페이지 */
    /* 게시글 상세조회API, 게시물 삭제API, 파일삭제API */

    /* 게시물 상세페이지에서 댓글버튼 누르면 뜨는 댓글창 */
    /* 댓글 등록API, 댓글 목록API, 댓글 수정API, 댓글 삭제API*/

    Intent intent;
    ListView listView;
    BoardDetailReplyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new BoardDetailReplyAdapter();
        listView.setAdapter(mAdapter);

        mAdapter.setOnAdapterBoardReplyDeleteClickListener(new BoardDetailReplyAdapter.OnAdapterBoardReplyDeleteClickListener() {
            @Override
            public void onAdapterBoardReplyDeleteClick(BoardDetailReplyAdapter adapter, BoardDetailReplyViewHolder view, Reply reply) {
                Toast.makeText(BoardDetailActivity.this, "삭제", Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnAdapterBoardReplyUpdateClickListener(new BoardDetailReplyAdapter.OnAdapterBoardReplyUpdateClickListener() {
            @Override
            public void onAdapterBoardReplyUpdateClick(BoardDetailReplyAdapter adapter, BoardDetailReplyViewHolder view, Reply reply) {
                Toast.makeText(BoardDetailActivity.this, "수정", Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.clear();
        initData();

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            Reply reply = new Reply();
            User user = new User();
            reply.setContent(i + "bread!!");
            reply.setRegDate("" + i);
            user.setNickname(i + "Writer");
            reply.setUserNumber(user);
            mAdapter.add(reply);
        }
    }
}
