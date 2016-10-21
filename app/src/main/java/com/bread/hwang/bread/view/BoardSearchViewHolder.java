package com.bread.hwang.bread.view;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bread.hwang.bread.R;
import com.bread.hwang.bread.data.Board;
import com.bumptech.glide.Glide;

/**
 * Created by Hwang on 2016-10-21.
 */

public class BoardSearchViewHolder extends FrameLayout {
    Board board;

    ImageView profileImage;
    TextView userName, content, date;

    public BoardSearchViewHolder(Context context) {
        super(context);
        initData();
    }

    private void initData() {
        inflate(getContext(), R.layout.view_boardsearch, this);
        profileImage = (ImageView) findViewById(R.id.image_profile);
        userName = (TextView) findViewById(R.id.text_username);
        content = (TextView) findViewById(R.id.text_content);
        date = (TextView) findViewById(R.id.text_date);

        content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (listener != null) {
                    listener.onContentClick(BoardSearchViewHolder.this, board);
                }
                return false;
            }
        });
    }

    public void setBoardSearch(Board board) {
        this.board = board;

        Glide.with(profileImage.getContext())
                .load(board.getUserNumber().getImagePath())
                .into(profileImage);

        userName.setText(board.getUserNumber().getNickname());
        content.setText(board.getContent());
        date.setText(board.getRegDate());
    }

    public interface OnContentClickListener {
        public void onContentClick(BoardSearchViewHolder boardSearchViewHolder, Board board);
    }

    OnContentClickListener listener;

    public void setOnContentClickListener(OnContentClickListener listener) {
        this.listener = listener;
    }

}
