package com.bread.hwang.bread.board;


import android.app.Activity;
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
import android.widget.Toast;

import com.bread.hwang.bread.MainActivity;
import com.bread.hwang.bread.R;
import com.bread.hwang.bread.adapter.BoardListAdapter;
import com.bread.hwang.bread.data.Board;
import com.bread.hwang.bread.data.BoardData;
import com.bread.hwang.bread.data.NetworkResult;
import com.bread.hwang.bread.data.User;
import com.bread.hwang.bread.manager.NetworkManager;
import com.bread.hwang.bread.manager.NetworkRequest;
import com.bread.hwang.bread.request.BoardListSearchRequest;
import com.bread.hwang.bread.request.BoardWriteRequest;
import com.bread.hwang.bread.view.BoardListViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardListFragment extends Fragment {
/* boardNum, */

    Intent intent;
    BoardListAdapter mAdapter;
    GridView gridView;
    int boardNum;
    int position;
    int realBoardNum;

    private static final int BOARD_NUM_REQUEST_CODE = 100;
    private static final String TAG_SEARCH_TYPE = "searchtype";

    public BoardListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board_list, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);

        Toast.makeText(getContext(), "boardnum : " + boardNum, Toast.LENGTH_SHORT).show();

        FloatingActionButton fb = (FloatingActionButton) view.findViewById(R.id.floating);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), BoardWriteActivity.class);
                intent.putExtra(TAG_SEARCH_TYPE, 1);
                intent.putExtra("boardnum", realBoardNum);
                startActivityForResult(intent, BOARD_NUM_REQUEST_CODE);

            }
        });

        mAdapter = new BoardListAdapter();
        gridView.setAdapter(mAdapter);

        String lastBoardNum = "lastBoardNum";
        BoardListSearchRequest listRequest = new BoardListSearchRequest(getContext(), 10, lastBoardNum);
        NetworkManager.getInstance().getNetworkData(listRequest, new NetworkManager.OnResultListener<NetworkResult<List<BoardData>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<BoardData>>> request, NetworkResult<List<BoardData>> result) {
                List<BoardData> board = result.getResult();
                mAdapter.clear();
                mAdapter.addAll(board);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<BoardData>>> request, int errorCode, String errorMessage, Throwable exception) {

            }
        });

        mAdapter.setOnAdapterImageClickListener(new BoardListAdapter.OnAdapterImageClickListener() {
            @Override
            public void onAdapterImageClick(BoardListAdapter adapter, BoardListViewHolder view, Board board) {
                position = (Integer)view.getTag();
                realBoardNum = board.getNumber();

                intent = new Intent(getContext(), BoardDetailActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("boardnum", realBoardNum);
                startActivity(intent);
            }
        });

        return view;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BOARD_NUM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            boardNum = data.getIntExtra("num", 1);
            Toast.makeText(getContext(), "boardnum:" + boardNum, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        String lastBoardNum = "lastBoardNum";
        BoardListSearchRequest listRequest = new BoardListSearchRequest(getContext(), 10, lastBoardNum);
        NetworkManager.getInstance().getNetworkData(listRequest, new NetworkManager.OnResultListener<NetworkResult<List<BoardData>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<BoardData>>> request, NetworkResult<List<BoardData>> result) {
                List<BoardData> board = result.getResult();
                mAdapter.clear();
                mAdapter.addAll(board);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<BoardData>>> request, int errorCode, String errorMessage, Throwable exception) {

            }
        });
    }
}
