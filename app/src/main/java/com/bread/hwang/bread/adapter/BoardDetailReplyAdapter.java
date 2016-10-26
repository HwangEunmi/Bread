package com.bread.hwang.bread.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.data.Reply;
import com.bread.hwang.bread.view.BoardDetailReplyViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hwang on 2016-10-21.
 */

public class BoardDetailReplyAdapter extends BaseAdapter implements BoardDetailReplyViewHolder.OnBoardReplyUpdateClickListener, BoardDetailReplyViewHolder.OnBoardReplyDeleteClickListener {
    List<Reply> items = new ArrayList<>();

    public void add(Reply reply) {
        items.add(reply);
        notifyDataSetChanged();
    }

    public void addAll(List<Reply> reply) {
        this.items.addAll(reply);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void set(int position, Reply reply) {
        items.set(position, reply);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BoardDetailReplyViewHolder view;
        if (convertView == null) {
            view = new BoardDetailReplyViewHolder(parent.getContext());

            view.setOnBoardReplyUpdateClickListener(this);
            view.setOnBoardReplyDeleteClickListener(this);
            convertView.setTag(view);
        } else {
            view = (BoardDetailReplyViewHolder) convertView.getTag();
        }
        view.setReply(items.get(position));

        return view;
    }

    public interface OnAdapterBoardReplyDeleteClickListener {
        public void onAdapterBoardReplyDeleteClick(BoardDetailReplyAdapter adapter, BoardDetailReplyViewHolder view, Reply reply);
    }

    OnAdapterBoardReplyDeleteClickListener mDeleteListener;

    public void setOnAdapterBoardReplyDeleteClickListener(OnAdapterBoardReplyDeleteClickListener listener) {
        mDeleteListener = listener;
    }

    @Override
    public void onBoardReplyDeleteClick(BoardDetailReplyViewHolder view, Reply reply) {
        if (mDeleteListener != null) {
            /* ViewHolder쪽에서 리스너에 Tag처리 해주고 여기서는 set해주기 */
            mDeleteListener.onAdapterBoardReplyDeleteClick(this, view, reply);
        }
    }

    public interface OnAdapterBoardReplyUpdateClickListener {
        public void onAdapterBoardReplyUpdateClick(BoardDetailReplyAdapter adapter, BoardDetailReplyViewHolder view, Reply reply);
    }

    OnAdapterBoardReplyUpdateClickListener mUpdateListener;

    public void setOnAdapterBoardReplyUpdateClickListener(OnAdapterBoardReplyUpdateClickListener listener) {
        mUpdateListener = listener;
    }

    @Override
    public void onBoardReplyUpdateClick(BoardDetailReplyViewHolder view, Reply reply) {
        if (mUpdateListener != null) {
            mUpdateListener.onAdapterBoardReplyUpdateClick(this, view, reply);
        }
    }
}
