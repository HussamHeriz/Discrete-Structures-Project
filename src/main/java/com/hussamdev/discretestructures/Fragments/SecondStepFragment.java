package com.hussamdev.discretestructures.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hussamdev.discretestructures.DSClasses.Function;
import com.hussamdev.discretestructures.DSClasses.RST_Relation;
import com.hussamdev.discretestructures.DSClasses.Relation;
import com.hussamdev.discretestructures.R;
import com.hussamdev.discretestructures.StaticVals;
import com.shawnlin.numberpicker.NumberPicker;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondStepFragment extends Fragment {

    public static Relation r;
    NumberPicker numberPicker1,numberPicker2;
    int domain[];
    int codomain[];
    public SecondStepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.fragment_second_step, container, false);

        if(StaticVals.typeOfRelation == 1)
        {
            StaticVals.codomainList = StaticVals.domainList;
        }

        domain = new int[StaticVals.domainList.size()];
        for(int i=0;i<StaticVals.domainList.size();i++){
            domain[i] = StaticVals.domainList.get(i);
        }

        codomain = new int[StaticVals.codomainList.size()];
        for(int i=0;i<StaticVals.codomainList.size();i++){
            codomain[i] = StaticVals.codomainList.get(i);
        }

        if(r == null)
            if(StaticVals.typeOfRelation == 1)
                r = new RST_Relation(domain);
            else if(StaticVals.typeOfRelation == 2)
                r = new Function(domain,codomain);
            else
                r = new Relation(domain,codomain);


        numberPicker1 = v.findViewById(R.id.number_picker);
        numberPicker2 = v.findViewById(R.id.number_picker2);


        String[] numbers = new String[StaticVals.domainList.size()];
        for(int i=0;i<StaticVals.domainList.size();i++){
            numbers[i] = StaticVals.domainList.get(i).toString();
        }
        numberPicker1.setMaxValue(StaticVals.domainList.size());
        numberPicker1.setMinValue(1);
        numberPicker1.setDisplayedValues(numbers);

        numbers = new String[StaticVals.codomainList.size()];
        for(int i=0;i<StaticVals.codomainList.size();i++){
            numbers[i] = StaticVals.codomainList.get(i).toString();
        }

        numberPicker2.setMaxValue(StaticVals.codomainList.size());
        numberPicker2.setMinValue(1);
        numberPicker2.setDisplayedValues(numbers);

        updateRelation(v);

        v.findViewById(R.id.addElement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = numberPicker1.getValue();
                int y = numberPicker2.getValue();

                x = StaticVals.domainList.get(x-1);
                y = StaticVals.codomainList.get(y-1);

                if(r.addElement(x,y)){
                    Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
                    updateRelation(v);
                }
            }
        });

        v.findViewById(R.id.removeElement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = numberPicker1.getValue();
                int y = numberPicker2.getValue();

                x = StaticVals.domainList.get(x-1);
                y = StaticVals.codomainList.get(y-1);

                if(r.removeElement(x,y)){
                    Toast.makeText(getActivity(), "Removed Successfully", Toast.LENGTH_SHORT).show();
                    updateRelation(v);
                }
            }
        });

        return v;
    }

    public void updateRelation(View v){
        ((TextView) v.findViewById(R.id.relation)).setText(r.toString());
    }

}
