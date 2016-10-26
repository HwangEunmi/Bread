package com.bread.hwang.bread.board;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bread.hwang.bread.MainActivity;
import com.bread.hwang.bread.R;
import com.bread.hwang.bread.adapter.BoardSearchAdapter;
import com.bread.hwang.bread.adapter.BoardSearchSpinnerAdapter;
import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.data.User;
import com.bread.hwang.bread.view.BoardSearchViewHolder;

public class BoardSearchActivity extends AppCompatActivity {
    /* 게시글 검색하는 액티비티(조건에 따라서) */
    /*게시글 목록(+검색API)  */
    /* Spinner의 구성이 작성자, 날짜인데 키워드가 있을경우 작성자검색/ 없으면 날짜순
    (BeautyTipFragment쪽 참고) 게시물 검색해오기 (일단 페이징 처리 ㄴㄴ, 한번에 뿌리기)*/

    Toolbar toolbar;
    TextView toolbarTitle;
    Intent intent;
    Spinner spinner;
    ListView listView;
    BoardSearchAdapter mAdapter;
    BoardSearchSpinnerAdapter spinnerAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_search);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbarTitle = (TextView)findViewById(R.id.text_toolbar_title);
        toolbarTitle.setText("게시물 찾기 화면");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new BoardSearchAdapter();
        mAdapter.setOnAdapterContentClickListener(new BoardSearchAdapter.OnAdapterContentClickListener() {
            @Override
            public void onAdapterContentClick(BoardSearchAdapter adapter, BoardSearchViewHolder view, Board board) {
                intent = new Intent(BoardSearchActivity.this, BoardDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });

        listView.setAdapter(mAdapter);

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.clear();
                initData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.clear();
                return false;
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerAdapter = new BoardSearchSpinnerAdapter();
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    Toast.makeText(BoardSearchActivity.this, "작성자", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BoardSearchActivity.this, "날짜", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /*Toolbar의 Negative버튼으로 교체*/

        initSpinner();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            Board board = new Board();
            User user = new User();

            user.setNickname("빵순이");
            board.setUserNumber(user);
            board.setContent(i + "빵이 좋아요");
            board.setRegDate(i + "");
            mAdapter.add(board);
        }
    }

    private void initSpinner() {
        String[] arrays = getResources().getStringArray(R.array.search);
        spinnerAdapter.addAll(arrays);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent = new Intent(BoardSearchActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
