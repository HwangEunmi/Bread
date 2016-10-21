package com.bread.hwang.bread.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.view.BoardListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hwang on 2016-10-20.
 */

public class BoardListAdapter extends BaseAdapter implements BoardListViewHolder.OnBoardItemClickListener {
    List<Board> items = new ArrayList<>();

    public void add(Board data) {
        items.add(data);
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
        BoardListViewHolder view;
        if (convertView == null) {
            view = new BoardListViewHolder(parent.getContext());

            view.setOnBoardItemClickListener(this);
        } else {
            view = (BoardListViewHolder) convertView;
        }
        view.setBoardList(items.get(position));

        return view;
    }

    @Override
    public void onBoardItemClick(BoardListViewHolder view, Board board) {
        if (mAdapterListener != null) {
            mAdapterListener.onAdapterImageClick(this, view, board);
        }
    }


    public interface OnAdapterImageClickListener {
        public void onAdapterImageClick(BoardListAdapter adapter, BoardListViewHolder view, Board board);
    }

    OnAdapterImageClickListener mAdapterListener;

    public void setOnAdapterImageClickListener(OnAdapterImageClickListener listener) {
        mAdapterListener = listener;
    }


}
