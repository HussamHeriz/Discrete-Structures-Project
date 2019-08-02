package com.hussamdev.discretestructures.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.hussamdev.discretestructures.Composition;
import com.hussamdev.discretestructures.DSClasses.Function;
import com.hussamdev.discretestructures.DSClasses.RST_Relation;
import com.hussamdev.discretestructures.DSClasses.Relation;
import com.hussamdev.discretestructures.R;
import com.hussamdev.discretestructures.StaticVals;
import com.shawnlin.numberpicker.NumberPicker;

public class ThirdStepFragment extends Fragment {

    Relation r;
    TextView ddef,range,relation,equivalence_classes;
    CheckBox reflexive,symmetric,transitive,equivlance;
    NumberPicker np1,np2;
    String[] nb1,nb2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_third_step, container, false);

        r = StaticVals.ssf.r;

        ddef = v.findViewById(R.id.ddef);
        range = v.findViewById(R.id.range);
        relation = v.findViewById(R.id.relation);


/*
        TextView ddef,range,relation,equivalence_classes;
        CheckBox reflexive,symmetric,transitive,equivlance;
*/
    
        ddef.setText(r.domainOfDef());
        range.setText(r.range());
        relation.setText(r.toString());





        if(StaticVals.typeOfRelation == 1 || StaticVals.typeOfRelation==2){

            ViewStub stub = v.findViewById(R.id.layout_stub);

            if(StaticVals.typeOfRelation == 1)
                stub.setLayoutResource(R.layout.rst_relation);
            else if(StaticVals.typeOfRelation == 2)
                stub.setLayoutResource(R.layout.function);

            final View stub_layout = stub.inflate();

            if(StaticVals.typeOfRelation == 1)
            {


                equivalence_classes = stub_layout.findViewById(R.id.equivalence_classes);

                reflexive = (CheckBox) stub_layout.findViewById(R.id.reflexive);
                symmetric = (CheckBox) stub_layout.findViewById(R.id.symmetric);
                transitive = (CheckBox) stub_layout.findViewById(R.id.transitive);
                equivlance = (CheckBox) stub_layout.findViewById(R.id.equivlance);

                RST_Relation rst_relation = (RST_Relation) r;
                reflexive.setChecked(rst_relation.isReflexive());
                symmetric.setChecked(rst_relation.isSymmetric());
                transitive.setChecked(rst_relation.isTransitive());
                equivlance.setChecked(rst_relation.isEquivalence());

                equivalence_classes.setText(rst_relation.printEqClasses());

                WebView webview = stub_layout.findViewById(R.id.webview);
                webview.getSettings().setJavaScriptEnabled(true);
                String dataArr[] = StaticVals.htmlData;
                String data = dataArr[0]+dataArr[1];
                data+=getNodes(rst_relation.getDomain());
                data+=dataArr[2]+dataArr[3];
                String relationData = rst_relation.toString();
                relationData = relationData.substring(1,relationData.length()-1);
                data+=getArrows(relationData);
                data+=dataArr[4];
                webview.loadDataWithBaseURL("file:///android_asset/",data,"text/html","utf-8","file:///android_asset/index.html");
                webview.reload();


            } else if(StaticVals.typeOfRelation == 2)
            {

                CheckBox is_left_total = stub_layout.findViewById(R.id.is_left_total);
                CheckBox is_right_total = stub_layout.findViewById(R.id.is_right_total);
                CheckBox injective = stub_layout.findViewById(R.id.injective);
                CheckBox surjective = stub_layout.findViewById(R.id.surjective);
                CheckBox bijective = stub_layout.findViewById(R.id.bijective);

                final Function f = (Function) r;
                is_left_total.setChecked(f.isLeftTotal());
                is_right_total.setChecked(f.isRightTotal());
                injective.setChecked(f.isInjective());
                surjective.setChecked(f.isSurjective());
                bijective.setChecked(f.isBijective());

                stub_layout.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        TextView fname = stub_layout.findViewById(R.id.fname);
                        String fnameStr = fname.getText().toString();

                        if(!fnameStr.isEmpty() && !StaticVals.names.contains(fnameStr)){
                            if(!StaticVals.functions.contains(f))
                                StaticVals.functions.add(f);
                            StaticVals.names.add(fnameStr);
                            fname.setEnabled(false);
                            Toast.makeText(getActivity(), "Function "+fnameStr+ " Added Successfully", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                nb1 = f.domainOfDef(0);
                nb2 = f.range(0);


                np1  = stub_layout.findViewById(R.id.number_picker1);
                np1.setMaxValue(nb1.length);
                np1.setMinValue(1);
                np1.setDisplayedValues(nb1);

                np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        int x = Integer.parseInt(nb1[newVal-1]);
                        ((TextView)stub_layout.findViewById(R.id.imageof)).setText(imageOfX(x,f));
                    }
                });


                np2  = stub_layout.findViewById(R.id.number_picker2);
                np2.setMaxValue(nb2.length);
                np2.setMinValue(1);
                np2.setDisplayedValues(nb2);


                np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        int y = Integer.parseInt(nb2[newVal-1]);
                        ((TextView)stub_layout.findViewById(R.id.preimageof)).setText(preimageOfY(y,f));
                    }
                });

            }
        }
        return v;
    }

    public String getNodes(int nodes[]){
        String str = "";
        for(int i=0;i<nodes.length;i++)
        {
            str+="{id: "+nodes[i]+", label: '"+nodes[i]+"' },";
        }
        return str;
    }

    public String getArrows(String relation)
    {

        relation = relation.replaceAll("[(]","");
        relation = relation.replace(")","");

        String relationArr[] = relation.split(",");

        String str = "";

        for(int i=0;i<relationArr.length;i+=2)
        {
            String x = relationArr[i];
            String y = relationArr[i+1];

            str+="{from: "+x+", to:"+y+", arrows:'to',";


            if(x.equals(y))
                str+="dashes:true,color:{color:'red'}}";
            else {
                str+="color:{color:'";
                int xInt = Integer.parseInt(x);
                int yInt = Integer.parseInt(y);
                if(xInt - yInt > 0)
                    str+="black'}}";
                else
                    str+="blue'}}";
            }

            str+=",";



        }
        return str;

    }

    public String imageOfX(int x,Function f) {
        String str = "f("+x+")=";
        try {
            int y = f.getImage(x);
            str+=y;
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String preimageOfY(int y,Function f) {
        String str = "f(";
        try {
            int x = f.getPreimage(y);
            str+=x+")="+y;
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
