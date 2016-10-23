package com.bread.hwang.bread.view;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bread.hwang.bread.R;
import com.bread.hwang.bread.data.Reply;
import com.bumptech.glide.Glide;

/**
 * Created by Hwang on 2016-10-21.
 */

public class BoardDetailReplyViewHolder extends FrameLayout {

    ImageView profileImage;
    TextView userName, content, date;
    ImageButton updateButton, deleteButton;

    public BoardDetailReplyViewHolder(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_detail_reply, this);

        profileImage = (ImageView) findViewById(R.id.image_reply_profile);
        userName = (TextView) findViewById(R.id.text_reply_username);
        content = (TextView) findViewById(R.id.text_reply_content);
        date = (TextView) findViewById(R.id.text_reply_date);
        updateButton = (ImageButton) findViewById(R.id.btn_reply_update);
        deleteButton = (ImageButton) findViewById(R.id.btn_reply_delete);

        updateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateListener != null) {
                    updateListener.onBoardReplyUpdateClick(BoardDetailReplyViewHolder.this, reply);
                }
            }
        });

        deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteListener != null) {
                    deleteListener.onBoardReplyDeleteClick(BoardDetailReplyViewHolder.this, reply);
                }
            }
        });
    }

    Reply reply;

    public void setReply(Reply reply) {
        this.reply = reply;
        Glide.with(profileImage.getContext())
                .load(reply.getUserNumber().getImagePath())
                .into(profileImage);

        userName.setText(reply.getUserNumber().getNickname());
        content.setText(reply.getContent());
        date.setText(reply.getRegDate());
    }

    public interface OnBoardReplyUpdateClickListener {
        public void onBoardReplyUpdateClick(BoardDetailReplyViewHolder view, Reply reply);
    }

    OnBoardReplyUpdateClickListener updateListener;

    public void setOnBoardReplyUpdateClickListener(OnBoardReplyUpdateClickListener listener) {
        updateListener = listener;
    }

    public interface OnBoardReplyDeleteClickListener {
        public void onBoardReplyDeleteClick(BoardDetailReplyViewHolder view, Reply reply);
    }

    OnBoardReplyDeleteClickListener deleteListener;

    public void setOnBoardReplyDeleteClickListener(OnBoardReplyDeleteClickListener listener) {
        deleteListener = listener;
    }

}
