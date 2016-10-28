package com.bread.hwang.bread.board;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bread.hwang.bread.MyApplication;
import com.bread.hwang.bread.R;
import com.bread.hwang.bread.adapter.BoardDetailReplyAdapter;
import com.bread.hwang.bread.data.Reply;
import com.bread.hwang.bread.data.User;
import com.bread.hwang.bread.manager.PropertyManager;
import com.bread.hwang.bread.view.BoardDetailReplyViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

public class BoardDetailActivity extends AppCompatActivity {
 /* 게시글 상세페이지 */
    /* 게시글 상세조회API, 게시물 삭제API, 파일삭제API */

    /* 게시물 상세페이지에서 댓글버튼 누르면 뜨는 댓글창 */
    /* 댓글 등록API, 댓글 목록API, 댓글 수정API, 댓글 삭제API*/

    Intent intent;
    ListView listView;
    BoardDetailReplyAdapter mAdapter;
    EditText replyEdit;
    ImageButton replyUpdate, replyDelete;
    AlertDialog dialog;
    Toolbar toolbar;
    TextView toolbarTitle;

    int position;
    String tempReply;

    private static final String TAG_REPLY_UPDATE = "replyupdate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbarTitle = (TextView)findViewById(R.id.text_toolbar_title);
        toolbarTitle.setText("게시물 상세 화면");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        Bundle bundle = new Bundle();
//        bundle.getBundle("replydata");
//        Toast.makeText(BoardDetailActivity.this, "replydata" + replyDelete, Toast.LENGTH_SHORT).show();

        View replyList = getLayoutInflater().inflate(R.layout.view_detail_reply, null);
        replyUpdate = (ImageButton) replyList.findViewById(R.id.btn_reply_update);
        replyDelete = (ImageButton) replyList.findViewById(R.id.btn_reply_delete);
        /* 나중에 View. GONE으로 수정 */
        replyUpdate.setVisibility(View.VISIBLE);
        replyDelete.setVisibility(View.VISIBLE);

        listView = (ListView) findViewById(R.id.listView);

        mAdapter = new BoardDetailReplyAdapter();
        listView.setAdapter(mAdapter);

        mAdapter.setOnAdapterBoardReplyDeleteClickListener(new BoardDetailReplyAdapter.OnAdapterBoardReplyDeleteClickListener() {
            @Override
            public void onAdapterBoardReplyDeleteClick(BoardDetailReplyAdapter adapter, BoardDetailReplyViewHolder view, Reply reply) {
                position = (Integer) view.getTag();
                getReplyDeleteList(position, reply);

            }
        });

        mAdapter.setOnAdapterBoardReplyUpdateClickListener(new BoardDetailReplyAdapter.OnAdapterBoardReplyUpdateClickListener() {
            @Override
            public void onAdapterBoardReplyUpdateClick(BoardDetailReplyAdapter adapter, BoardDetailReplyViewHolder view, Reply reply) {
                position = (Integer) view.getTag();
                getReplyUpdateList(position, tempReply);
            }
        });


        replyEdit = (EditText) findViewById(R.id.edit_comment);

        Button replyButton = (Button) findViewById(R.id.btn_set);
        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String replyText = replyEdit.getText().toString();
                replyEdit.setTag("");

                Reply reply = new Reply();
                User user = new User();
                user.setNickname("마카롱");
                user.setImagePath("" + R.drawable.default_bread);
                reply.setUserNumber(user);
                reply.setRegDate("2016.10.1");
                reply.setContent(replyText);

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

    MenuItem updateMenuItem;
    MenuItem deleteMenuItem;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_board_detail, menu);

        updateMenuItem = menu.findItem(R.id.board_update);
        deleteMenuItem = menu.findItem(R.id.board_delete);

        /* 나중에 false로 바꾸기(네트워크 할때)*/
        updateMenuItem.setVisible(true);
        deleteMenuItem.setVisible(true);
        return true;
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
        return super.onOptionsItemSelected(item);
    }

    /* 수정/삭제 메뉴 자기 게시글에만 보이게 */
    private void getBoardInfo() {
        /* 게시판의 상세정보를 불러오는 API */

        // Board board = result.getResult();

        //if(PropertyManager.getInstance().getUserId() == board.getUser().getId()){
//        updateMenuItem.setVisible(true);
//        deleteMenuItem.setVisible(true);
//    } else {
//        updateMenuItem.setVisible(false);
//        deleteMenuItem.setVisible(false);
    }

    public void setTempReply(int position, String content) {
        int count = mAdapter.getCount();

        if (count > 0) {
            Reply reply = new Reply();
            reply.setContent(content);

            mAdapter.set(position, reply);
        }
    }

    public void getReplyUpdateList(int position, String content) {
        FragmentManager fm = getSupportFragmentManager();
        ReplyUpdateFragment dialog = new ReplyUpdateFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        dialog.setArguments(bundle);
        dialog.show(fm, "commentdialog");

        setTempReply(position, content);
         /* Bundle로 boardid등 넘기기*/

    }

    private void getReplyDeleteList(int position, Reply reply) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Delete Reply");
        builder.setMessage("Delete Reply...");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                     /* 댓글 삭제 API */

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
