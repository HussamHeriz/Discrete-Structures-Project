package com.hussamdev.discretestructures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hussamdev.discretestructures.Fragments.FirstStepFragment;
import com.hussamdev.discretestructures.Fragments.SecondStepFragment;
import com.hussamdev.discretestructures.Fragments.ThirdStepFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button relation,rst,function,c_functions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relation = (Button) findViewById(R.id.relation);
        rst = (Button) findViewById(R.id.rst);
        function = (Button) findViewById(R.id.function);
        relation.setOnClickListener(this);
        rst.setOnClickListener(this);
        function.setOnClickListener(this);

        c_functions = (Button) findViewById(R.id.c_functions);
        c_functions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Composition.class));
            }
        });

    }

    @Override
    public void onClick(View view) {

        StaticVals.ssf.r = null;
        StaticVals.fsf = new FirstStepFragment();
        StaticVals.ssf = new SecondStepFragment();
        StaticVals.tsf = new ThirdStepFragment();
        StaticVals.domainList = new ArrayList<>();
        StaticVals.codomainList = new ArrayList<>();

        if(view == rst)
            StaticVals.typeOfRelation = 1;
        else if(view == function)
            StaticVals.typeOfRelation = 2;
        else
            StaticVals.typeOfRelation = 0;

        startActivity(new Intent(MainActivity.this,MainActivity2.class));

    }
}
