package com.bread.hwang.bread;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bread.hwang.bread.board.BoardListFragment;
import com.bread.hwang.bread.mypage.MyPageFragment;

public class MainActivity extends AppCompatActivity {
    /* 게시물검색버튼 (Toolbar에 붙일거) 이거 menu로 만들기 */
    /* FragmentTabHost에다가 Fragment들(BoardListFragment, MyPageFragment) 셋팅 */
    Fragment f;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* BoardListFragment랑 MyPageFragment랑 TabHost 셋팅하기 (네비게이션 다 끝나고) */

        f = new BoardListFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, f).commit();

        Button blist = (Button) findViewById(R.id.btn_board_list);
        blist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f instanceof BoardListFragment)
                    return;

                f = new BoardListFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, f).commit();
            }
        });

        Button mypage = (Button) findViewById(R.id.btn_mypage);
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f instanceof MyPageFragment)
                    return;

                f = new MyPageFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, f).commit();
            }
        });
    }
}
