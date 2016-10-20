package com.bread.hwang.bread.mypage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bread.hwang.bread.R;

public class UpdateProfileActivity extends AppCompatActivity {
    /* 마이 페이지 수정하는 화면 */
    /* 비밀번호 확인API, 회원수정API */

     /* 갤러리 연동하니까 Permission받기 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
    }
}
