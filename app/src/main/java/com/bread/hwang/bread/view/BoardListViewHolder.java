package com.bread.hwang.bread.view;

import android.content.Context;
import android.provider.ContactsContract;
import android.renderscript.BaseObj;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bread.hwang.bread.R;
import com.bread.hwang.bread.data.Board;
import com.bumptech.glide.Glide;

/**
 * Created by Hwang on 2016-10-20.
 */

public class BoardListViewHolder extends FrameLayout {
    Board board;
    ImageView boardImage, profileImage;
    TextView content, userName, date, commentCount, fileCount;

    public BoardListViewHolder(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_boardlist, this);
        boardImage = (ImageView) findViewById(R.id.image_board);
        profileImage = (ImageView) findViewById(R.id.image_profile);
        content = (TextView) findViewById(R.id.text_content);
        userName = (TextView) findViewById(R.id.text_username);
        commentCount = (TextView) findViewById(R.id.text_comment_count);
        fileCount = (TextView) findViewById(R.id.text_file_count);
        date = (TextView) findViewById(R.id.text_date);

        boardImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onBoardItemClick(BoardListViewHolder.this, board);

                }
            }
        });
    }

    public void setBoardList(Board board) {
        this.board = board;

        Glide.with(profileImage.getContext())
                .load(board.getUserNumber().getImagePath())
                .into(profileImage);

        Glide.with(boardImage.getContext())
                .load(board.getImagePath())
                .into(boardImage);

        date.setText(board.getRegDate());
        content.setText(board.getContent());
        userName.setText(board.getUserNumber().getNickname());
        commentCount.setText("" + board.getReplyCount());
        fileCount.setText("" + fileCount);
    }

    public interface OnBoardItemClickListener {
        public void onBoardItemClick(BoardListViewHolder view, Board board);
        /* RecyclerView처럼 int position추가 안해도 되는지 */
    }

    OnBoardItemClickListener listener;

    public void setOnBoardItemClickListener(OnBoardItemClickListener listener) {
        this.listener = listener;
    }


}
