package com.nandohusni.training_guru;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.nandohusni.training_guru.base.BaseActivity;
import com.nandohusni.training_guru.ui.home.HomeActivity;
import com.nandohusni.training_guru.ui.signIn.LoginActivity;

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
