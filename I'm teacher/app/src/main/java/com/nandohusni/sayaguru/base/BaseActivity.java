package com.nandohusni.sayaguru.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nandohusni.sayaguru.utils.SessionManager;

public class BaseActivity extends AppCompatActivity {

    protected SessionManager sesi ;
    protected Context c ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        c = this ;
        sesi = new SessionManager(c);
    }
}
