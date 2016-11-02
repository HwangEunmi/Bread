package com.bread.hwang.bread.board;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
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
import com.bread.hwang.bread.data.BoardData;
import com.bread.hwang.bread.data.NetworkResult;
import com.bread.hwang.bread.data.User;
import com.bread.hwang.bread.manager.NetworkManager;
import com.bread.hwang.bread.manager.NetworkRequest;
import com.bread.hwang.bread.request.BoardListSearchRequest;
import com.bread.hwang.bread.util.DateCalculator;
import com.bread.hwang.bread.view.BoardSearchViewHolder;

import java.util.List;

public class BoardSearchActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarTitle;
    Intent intent;
    Spinner spinner;
    ListView listView;
    BoardSearchAdapter mAdapter;
    BoardSearchSpinnerAdapter spinnerAdapter;
    SearchView searchView;

    String searchType, keyWord, lastBoardNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_search);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.text_toolbar_title);
        toolbarTitle.setText("게시물 찾기 화면");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerAdapter = new BoardSearchSpinnerAdapter();
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              /* 나중에 지우기 */
                if (spinner.getSelectedItemPosition() == 0)
                    searchType = "name";
                else
                    searchType = "date";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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
                // initData();

                setSearchResult();
                lastBoardNum = "lastBoardNum";

                if (spinner.getSelectedItemPosition() == 0)
                    searchType = "name";
                else
                    searchType = "date";

                BoardListSearchRequest searchRequest = new BoardListSearchRequest(BoardSearchActivity.this, 10, lastBoardNum, searchType, keyWord);
                NetworkManager.getInstance().getNetworkData(searchRequest, new NetworkManager.OnResultListener<NetworkResult<List<BoardData>>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<List<BoardData>>> request, NetworkResult<List<BoardData>> result) {
                        List<BoardData> boards = result.getResult();
                        Toast.makeText(BoardSearchActivity.this, "keyWord: " + keyWord, Toast.LENGTH_SHORT).show();

                        mAdapter.clear();
                        mAdapter.addAll(boards);
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<List<BoardData>>> request, int errorCode, String errorMessage, Throwable exception) {

                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.clear();
                return false;
            }
        });

        /*Toolbar의 Negative버튼으로 교체*/

        initSpinner();
    }

    /* 키워드가 날짜일때 검색 */
    public void setSearchResult() {
        DateCalculator dateCalculator = new DateCalculator();

        keyWord = "" + searchView.getQuery();
    }

//    private void initData() {
//        for (int i = 0; i < 20; i++) {
//            Board board = new Board();
//            User user = new User();
//
//            user.setNickname("빵순이");
//            board.setUserNumber(user);
//            board.setContent(i + "빵이 좋아요");
//            board.setRegDate(i + "");
//            mAdapter.add(board);
//        }
//    }

    private void initSpinner() {
        String[] arrays = getResources().getStringArray(R.array.search);
        spinnerAdapter.addAll(arrays);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_cancel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_cancel) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
