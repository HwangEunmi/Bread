package com.bread.hwang.bread.mypage;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bread.hwang.bread.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdatePassFragment extends DialogFragment {
    EditText currentPass, passText, againText;
    Bundle extra;
    String userName, userPass;

    public UpdatePassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_pass, container, false);
        extra = getArguments();
        userName = extra.getString("username");
        userPass = extra.getString("userpass");

        currentPass = (EditText) view.findViewById(R.id.edit_pass);
        passText = (EditText) view.findViewById(R.id.edit_one);
        againText = (EditText) view.findViewById(R.id.edit_two);

        Button sendButton = (Button) view.findViewById(R.id.btn_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = passText.getText().toString();
                String once = againText.getText().toString();

                if (currentPass.equals(passText)) {
                    if (!pass.equals(once)) {
                        Toast.makeText(getContext(), "Not match", Toast.LENGTH_SHORT).show();
                    } else {
                    /* 회원 수정API */


                        Toast.makeText(getContext(), "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "비밀번호를 정확히 입력", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Dialog dialog = getDialog();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
