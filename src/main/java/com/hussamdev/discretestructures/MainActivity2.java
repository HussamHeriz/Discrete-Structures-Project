package com.hussamdev.discretestructures;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hussamdev.discretestructures.Fragments.FirstStepFragment;
import com.hussamdev.discretestructures.Fragments.SecondStepFragment;
import com.hussamdev.discretestructures.Fragments.ThirdStepFragment;

public class MainActivity2 extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.step1:
                    ft.replace(R.id.content,StaticVals.fsf);
                    ft.commit();
                    return true;
                case R.id.step2:
                    if(StaticVals.domainList.size()>0 && StaticVals.codomainList.size()>0 && StaticVals.typeOfRelation!=1 || StaticVals.domainList.size()>0 && StaticVals.typeOfRelation==1){
                        ft.replace(R.id.content,StaticVals.ssf);
                        ft.commit();
                        return true;
                    }
                    return false;
                case R.id.step3:
                    if(StaticVals.ssf.r != null){
                        ft.replace(R.id.content,StaticVals.tsf);
                        ft.commit();
                        return true;
                    }
                    return false;

            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FrameLayout frame = (FrameLayout) findViewById(R.id.content);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content,StaticVals.fsf);
        ft.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
