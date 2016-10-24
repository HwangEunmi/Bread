package com.bread.hwang.bread;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.bread.hwang.bread.manager.PropertyManager;

public class SplashActivity extends AppCompatActivity {
 /* 수초 이후 메인액티비티로 화면 전환*/

    Handler mHandler = new Handler(Looper.getMainLooper());
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /* 자동로그인 할 경우 여기서 세션 처리 */
        String id = PropertyManager.getInstance().getUserId();

        if(!TextUtils.isEmpty(id)){
            moveBoardList();
        }else {
            moveMainActivity();
        }
    }

    /* Main으로 이동 */
    private void moveMainActivity() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }

    private void moveBoardList() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}

