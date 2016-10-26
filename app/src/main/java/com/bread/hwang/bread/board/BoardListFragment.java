package com.bread.hwang.bread.board;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.bread.hwang.bread.MainActivity;
import com.bread.hwang.bread.R;
import com.bread.hwang.bread.adapter.BoardListAdapter;
import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.data.User;
import com.bread.hwang.bread.view.BoardListViewHolder;
import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardListFragment extends Fragment {
  /* 게시글 리스트 화면 */
    /* 게시물 목록(+검색)API */

    Intent intent;
    BoardListAdapter mAdapter;
    GridView gridView;

    public BoardListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board_list, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);

        FloatingActionButton fb = (FloatingActionButton) view.findViewById(R.id.floating);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), BoardWriteActivity.class);
                startActivity(intent);
            }
        });

        mAdapter = new BoardListAdapter();
        gridView.setAdapter(mAdapter);
        mAdapter.setOnAdapterImageClickListener(new BoardListAdapter.OnAdapterImageClickListener() {
            @Override
            public void onAdapterImageClick(BoardListAdapter adapter, BoardListViewHolder view, Board board) {
                intent = new Intent(getContext(), BoardDetailActivity.class);
                startActivity(intent);
            }
        });

        initData();

        return view;
    }

    private void initData() {

        for (int i = 0; i < 20; i++) {
            Board b = new Board();
            User user = new User();
            user.setNickname("빵");
           // user.setImagePath(getResources().getDrawable(R.drawable.default_user_profile).toString());
           // b.setImagePath("PreView");
            b.setUserNumber(user);
            b.setContent("hi hi hello hi hello ");
            b.setAudioCount(1);
            b.setImageCount(1);
            b.setVideoCount(1);
            b.setReplyCount(1);
            b.setFileCount(b.getAudioCount(),b.getImageCount(), b.getVideoCount());
            b.setRegDate("2016.10.11");
            mAdapter.add(b);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.activity_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_search) {
            Intent intent = new Intent(getContext(), BoardSearchActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
