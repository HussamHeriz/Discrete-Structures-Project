package com.hussamdev.discretestructures;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hussamdev.discretestructures.DSClasses.Function;
import com.hussamdev.discretestructures.Fragments.FirstStepFragment;
import com.hussamdev.discretestructures.Fragments.SecondStepFragment;
import com.hussamdev.discretestructures.Fragments.ThirdStepFragment;

import java.util.ArrayList;

public class Composition extends AppCompatActivity {
    Spinner spinner,spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composition);

        String[] names = new String[StaticVals.names.size()];
        for(int i=0;i<names.length;i++)
            names[i] = StaticVals.names.get(i);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,names);
        ArrayAdapter<String> adapter2 = adapter;

        spinner = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

        findViewById(R.id.composite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int x = spinner.getSelectedItemPosition();
                int y = spinner2.getSelectedItemPosition();

                try {
                    Function f = StaticVals.functions.get(x).composite(StaticVals.functions.get(y));
                    if(f != null){
                        StaticVals.domainList = new ArrayList<Integer>();
                        StaticVals.codomainList = new ArrayList<Integer>();
                        StaticVals.fsf = new FirstStepFragment();
                        StaticVals.ssf = new SecondStepFragment();
                        StaticVals.tsf = new ThirdStepFragment();

                        for (int i = 0; i < f.getDomain().length; i++) {
                            StaticVals.domainList.add(f.getDomain()[i]);
                        }

                        for (int i = 0; i < f.getCodomain().length; i++) {
                            StaticVals.codomainList.add(f.getCodomain()[i]);
                        }

                        StaticVals.ssf.r = f;
                        StaticVals.typeOfRelation = 2;

                        finish();
                        startActivity(new Intent(Composition.this, MainActivity2.class));
                    } else {
                        Toast.makeText(Composition.this, "Cannot Composite These Functions", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(Composition.this, "Cannot Composite These Functions", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}
