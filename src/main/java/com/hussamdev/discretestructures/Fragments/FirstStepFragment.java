package com.hussamdev.discretestructures.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hussamdev.discretestructures.R;
import com.hussamdev.discretestructures.StaticVals;

import java.util.ArrayList;


public class FirstStepFragment extends Fragment implements View.OnClickListener,TextWatcher{

    Button decrement,decrement2,increment,increment2;
    EditText display,display2,from,to,from2,to2;
    CheckBox cb,cb2;
    TextView domain,codomain;
    Button clear1,clear2;

    ArrayList<Integer> domainList;
    ArrayList<Integer> codomainList;

    public FirstStepFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first_step, container, false);

        decrement = v.findViewById(R.id.decrement);
        decrement2 = v.findViewById(R.id.decrement2);
        increment = v.findViewById(R.id.increment);
        increment2 = v.findViewById(R.id.increment2);

        decrement.setOnClickListener(this);
        decrement2.setOnClickListener(this);
        increment.setOnClickListener(this);
        increment2.setOnClickListener(this);

        display = v.findViewById(R.id.display);
        display2 = v.findViewById(R.id.display2);
        from = v.findViewById(R.id.from);
        from2 = v.findViewById(R.id.from2);
        to = v.findViewById(R.id.to);
        to2 = v.findViewById(R.id.to2);

        cb = v.findViewById(R.id.cb);
        cb2 = v.findViewById(R.id.cb2);



        domain = v.findViewById(R.id.domain);
        codomain = v.findViewById(R.id.codomain);

        clear1 = v.findViewById(R.id.clear1);
        clear2 = v.findViewById(R.id.clear2);

        if(StaticVals.typeOfRelation == 1)
        {
            decrement2.setVisibility(View.GONE);
            increment2.setVisibility(View.GONE);
            display2.setVisibility(View.GONE);
            from2.setVisibility(View.GONE);
            to2.setVisibility(View.GONE);
            cb2.setVisibility(View.GONE);
            codomain.setVisibility(View.GONE);
            clear2.setVisibility(View.GONE);

            ((TextView) v.findViewById(R.id.selectcodomain)).setVisibility(View.GONE);
            ((TextView) v.findViewById(R.id.right_par)).setVisibility(View.GONE);
            ((TextView) v.findViewById(R.id.left_par)).setVisibility(View.GONE);
            ((TextView) v.findViewById(R.id.comma)).setVisibility(View.GONE);
        }

        domainList = StaticVals.domainList;
        codomainList = StaticVals.codomainList;

        clear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                domain.setText("{}");
                domainList.removeAll(domainList);
                StaticVals.domainList.removeAll(StaticVals.domainList);
            }
        });

        clear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codomain.setText("{}");
                codomainList.removeAll(codomainList);
                StaticVals.codomainList.removeAll(StaticVals.codomainList);
            }
        });

        from.addTextChangedListener(this);
        to.addTextChangedListener(this);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    updateDomain(1);
                    display.setEnabled(false);
                } else {
                    display.setEnabled(true);
                }



            }
        });

        from2.addTextChangedListener(this);
        to2.addTextChangedListener(this);
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    updateCodomain(1);
                    display2.setEnabled(false);
                }else
                    display2.setEnabled(true);

            }
        });

        updateDomain(0);
        updateCodomain(0);


        return v;
    }

    @Override
    public void onClick(View view) {

        try {
            if (view == increment) {
                int x = Integer.parseInt(display.getText().toString());
                if (!domainList.contains(x))
                    domainList.add(x);
                updateDomain(0);
            } else if (view == decrement) {
                int x = Integer.parseInt(display.getText().toString());
                domainList.remove(new Integer(x));
                updateDomain(0);
            }
        }catch (Exception e){

        }


        try {
            if (view == increment2) {
                int x = Integer.parseInt(display2.getText().toString());
                if (!codomainList.contains(x))
                    codomainList.add(x);
                updateCodomain(0);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } else if (view == decrement2) {
                int x = Integer.parseInt(display2.getText().toString());
                codomainList.remove(new Integer(x));
                updateCodomain(0);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }catch (Exception e){

        }


    }

    public void updateDomain(int type){
        if(type == 1){
            String toString = to.getText().toString();
            String fromString = from.getText().toString();
            if(!toString.isEmpty() && cb.isChecked() && !fromString.isEmpty()){
                int b = Integer.parseInt(toString);
                int a = Integer.parseInt(fromString);
                domainList.remove(domainList);
                for(int i=a;i<=b;i++)
                    if(!domainList.contains(i))
                        domainList.add(i);
                updateDomain(0);
            }
        }
        String str = "{";
        for(int i=0;i<domainList.size();i++){
            str+=domainList.get(i);
            if(i<domainList.size()-1)
                str+=",";
        }
        str+="}";
        domain.setText(str);
        display.setText("");

    }

    public void updateCodomain(int type){
        if(type == 1){
            String toString = to2.getText().toString();
            String fromString = from2.getText().toString();
            if(!toString.isEmpty() && cb2.isChecked() && !fromString.isEmpty()){
                int b = Integer.parseInt(toString);
                int a = Integer.parseInt(fromString);
                codomainList.removeAll(codomainList);
                for(int i=a;i<=b;i++){
                        if(!codomainList.contains(i))
                            codomainList.add(i);
                }

                updateCodomain(0);
            }
        }
        String str = "{";
        for(int i=0;i<codomainList.size();i++){
            str+=codomainList.get(i);
            if(i<codomainList.size()-1)
                str+=",";
        }
        str+="}";
        codomain.setText(str);
        display2.setText("");
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        updateDomain(1);
        updateCodomain(1);
    }
}
