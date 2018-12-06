package com.nandohusni.sayaguru.ui.done;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.nandohusni.sayaguru.R;
import com.nandohusni.sayaguru.base.BaseActivity;
import com.nandohusni.sayaguru.ui.home.HomeActivity;

public class DoneActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_done,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.home2 :
                startActivity(new Intent(c,HomeActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
