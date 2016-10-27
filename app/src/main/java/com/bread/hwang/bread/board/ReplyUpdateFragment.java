package com.bread.hwang.bread.board;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bread.hwang.bread.R;
import com.bread.hwang.bread.adapter.BoardDetailReplyAdapter;
import com.bread.hwang.bread.data.Reply;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReplyUpdateFragment extends DialogFragment {

    EditText editContent;
    Button sendButton;
    String replyContent;

    private static final String TAG_REPLY_UPDATE = "replyupdate";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Bundle 값 받기 (id같은거) */
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_reply_update, container, false);

        editContent = (EditText) view.findViewById(R.id.edit_content);
        sendButton = (Button) view.findViewById(R.id.btn_send);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ReplyUpdateFragment fragment = new ReplyUpdateFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString(TAG_REPLY_UPDATE, replyContent);
//                fragment.setArguments(bundle);
                int position = (Integer)v.getTag();
                Reply reply = new Reply();
                replyContent = editContent.getText().toString();
                reply.setContent(replyContent);
                BoardDetailActivity activity = ((BoardDetailActivity)getActivity());
                activity.getReplyUpdateList(position, replyContent);

                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Dialog d = getDialog();
        WindowManager.LayoutParams params = d.getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        d.getWindow().setAttributes(params);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
