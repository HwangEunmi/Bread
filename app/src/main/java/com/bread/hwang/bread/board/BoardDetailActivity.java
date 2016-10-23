package com.bread.hwang.bread.board;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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
    EditText replyEdit;

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

        replyEdit = (EditText)findViewById(R.id.edit_comment);
        Button replyButton = (Button)findViewById(R.id.btn_set);
        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int count;
//                count = mAdapter.getCount() + 1;
                String replyText = replyEdit.getText().toString();

                Reply reply = new Reply();
                User user = new User();
                user.setNickname("마카롱");
                user.setImagePath(""+R.drawable.default_bread);
                reply.setUserNumber(user);
                reply.setRegDate("2016.10.1");
                reply.setContent(replyText);
             //   mAdapter.clear();
                mAdapter.add(reply);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* 나중에 자기 게시글일때만 수정, 삭제 가능하게 */
        getMenuInflater().inflate(R.menu.activity_board_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.board_update) {
            intent = new Intent(BoardDetailActivity.this, BoardWriteActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.board_delete) {
            /* 게시물 삭제 */
            Toast.makeText(BoardDetailActivity.this, "삭제합니다", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
