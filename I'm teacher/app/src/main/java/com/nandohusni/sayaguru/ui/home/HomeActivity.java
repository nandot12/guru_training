package com.nandohusni.sayaguru.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.nandohusni.sayaguru.R;
import com.nandohusni.sayaguru.ui.home.fragment.HomeFragment;
import com.nandohusni.sayaguru.ui.home.fragment.ProfilFragment;

public class HomeActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    FragmentTransaction move = getSupportFragmentManager().beginTransaction();
                    move.replace(R.id.container, new HomeFragment()).commit();
                    return true;
                case R.id.navigation_notifications:

                    FragmentTransaction move2 = getSupportFragmentManager().beginTransaction();
                    move2.replace(R.id.container, new ProfilFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentTransaction move = getSupportFragmentManager().beginTransaction();
        move.replace(R.id.container, new HomeFragment()).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
