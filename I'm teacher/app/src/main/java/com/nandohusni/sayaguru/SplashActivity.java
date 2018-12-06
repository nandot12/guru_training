package com.nandohusni.sayaguru;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.nandohusni.sayaguru.base.BaseActivity;
import com.nandohusni.sayaguru.ui.home.HomeActivity;
import com.nandohusni.sayaguru.ui.signIn.LoginActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(sesi.isLogin()){
                    startActivity(new Intent(SplashActivity.this,HomeActivity.class));

                }
                else{
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));

                }

                finish();



            }
        },4000);
    }
}
