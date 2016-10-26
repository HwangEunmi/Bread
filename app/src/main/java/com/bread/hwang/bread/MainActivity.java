package com.bread.hwang.bread;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Choreographer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bread.hwang.bread.board.BoardListFragment;
import com.bread.hwang.bread.board.BoardSearchActivity;
import com.bread.hwang.bread.mypage.MyPageFragment;

public class MainActivity extends AppCompatActivity {
    /* 게시물검색버튼 (Toolbar에 붙일거) 이거 menu로 만들기 */
    /* FragmentTabHost에다가 Fragment들(BoardListFragment, MyPageFragment) 셋팅 */


    FragmentTabHost tabHost;
    Toolbar toolbar;
    TextView toolbarTitle;

    public final static String TAG_TAB_BOARD = "board";
    public final static String TAG_TAB_MYPAGE = "mypage";

    public final static String LABEL_TAB_BOARD = "게시판";
    public final static String LABEL_TAB_MYPAGE = "마이페이지";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.text_toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        ImageView boardIndicator = new ImageView(this);
        boardIndicator.setImageResource(R.drawable.selector_tab_board);
        ImageView myPageIndicator = new ImageView(this);
        myPageIndicator.setImageResource(R.drawable.selector_tab_mypage);

        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec(TAG_TAB_BOARD).setIndicator(boardIndicator), BoardListFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec(TAG_TAB_MYPAGE).setIndicator(myPageIndicator), MyPageFragment.class, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_search) {
            Intent intent = new Intent(MainActivity.this, BoardSearchActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
