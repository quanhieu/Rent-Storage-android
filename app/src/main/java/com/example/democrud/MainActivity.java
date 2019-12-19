package com.example.democrud;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements AddFragment.OnFragmentInteractionListener, LoadFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FragmentTransaction transactionAdd = fragmentManager.beginTransaction();
                    AddFragment addFragment = new AddFragment();
                    transactionAdd.replace(R.id.container, addFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    FragmentTransaction transactionLoad = fragmentManager.beginTransaction();
                    LoadFragment loadFragment = new LoadFragment();
                    transactionLoad.replace(R.id.container, loadFragment).commit();
                    return true;

                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction transactionAdd = fragmentManager.beginTransaction();
        AddFragment addFragment = new AddFragment();
        transactionAdd.replace(R.id.container, addFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

