package com.bread.hwang.bread.board;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bread.hwang.bread.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardListFragment extends Fragment {
  /* 게시글 리스트 화면 */
    /* 게시물 목록(+검색)API */

    /* 게시글리스트에서 오디오, 동영상파일 유/무의 동그란 이미지 그거
     * 첨엔 View.GONE해놓고
    * 받아온 파일url에서 오디오, 동영상 있으면 View.VISIBLE) 해놓기
     * (이거 파싱하는게 (일단 오디오, 동영상파일의 url도 가져오는지 확인하기)(이거 url이 오는게 아니라
      * 이미지 하나만 오는거라서 이거 못함) (파일 갯수만 온다고 함)*/
    Intent intent;

    public BoardListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board_list, container, false);

        FloatingActionButton fb = (FloatingActionButton) view.findViewById(R.id.floating);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), BoardDetailActivity.class);
                startActivity(intent);
            }
        });
        Button search = (Button) view.findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), BoardSearchActivity.class);

                startActivity(intent);
            }
        });

        Button write = (Button) view.findViewById(R.id.btn_write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), BoardWriteActivity.class);
                startActivity(intent);
            }
        });

        /* 프래그먼트들은 TabHost 이거 셋팅하고 나서 네비게이션 구현 */
        return view;
    }

}
