package com.bread.hwang.bread.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.view.BoardSearchViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hwang on 2016-10-21.
 */

public class BoardSearchAdapter extends BaseAdapter implements BoardSearchViewHolder.OnContentClickListener {
    List<Board> items = new ArrayList<>();

    public void add(Board board) {
        items.add(board);
        notifyDataSetChanged();
    }

    public void addAll(List<Board> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
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
        BoardSearchViewHolder view;
        if (convertView == null) {
            view = new BoardSearchViewHolder(parent.getContext());
            view.setOnContentClickListener(this);
        } else {
            view = (BoardSearchViewHolder) convertView;
        }

        view.setBoardSearch(items.get(position));

        return view;
    }

    public interface OnAdapterContentClickListener {
        public void onAdapterContentClick(BoardSearchAdapter adapter, BoardSearchViewHolder view, Board board);
    }

    OnAdapterContentClickListener mAdapterListener;

    public void setOnAdapterContentClickListener(OnAdapterContentClickListener listener) {
        mAdapterListener = listener;
    }

    @Override
    public void onContentClick(BoardSearchViewHolder boardSearchViewHolder, Board board) {
        if (mAdapterListener != null) {
            mAdapterListener.onAdapterContentClick(this, boardSearchViewHolder, board);
        }
    }
}
