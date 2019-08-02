package com.hussamdev.discretestructures;

import com.hussamdev.discretestructures.DSClasses.Function;
import com.hussamdev.discretestructures.DSClasses.Relation;
import com.hussamdev.discretestructures.Fragments.FirstStepFragment;
import com.hussamdev.discretestructures.Fragments.SecondStepFragment;
import com.hussamdev.discretestructures.Fragments.ThirdStepFragment;

import java.util.ArrayList;

/**
 * Created by zcc on 20/11/17.
 */

public class StaticVals {
    public static int typeOfRelation = 0 ; // 0 Relation , 1 RST Relation , 2 Function
    public static FirstStepFragment fsf = new FirstStepFragment();
    public static SecondStepFragment ssf = new SecondStepFragment();
    public static ThirdStepFragment tsf = new ThirdStepFragment();

    public static ArrayList<Integer> domainList = new ArrayList<Integer>();
    public static ArrayList<Integer> codomainList = new ArrayList<Integer>();
    public static ArrayList<Function> functions = new ArrayList<Function>();
    public static ArrayList<String> names = new ArrayList<String>();

    public static String htmlData[] = {"<!doctype html><html><head><script type=\"text/javascript\" src=\"vis.js\"></script><link href=\"vis-network.min.css\" rel=\"stylesheet\" type=\"text/css\" /><style type=\"text/css\">#mynetwork {width: 100%;height: 400px;border: 1px solid lightgray;}</style></head><body><div id=\"mynetwork\"></div><script type=\"text/javascript\">",
            "var nodes = new vis.DataSet([",
            "]);",
            "var edges = new vis.DataSet([",
            "]);var container = document.getElementById('mynetwork');var data = {nodes: nodes,edges: edges};var options = {autoResize:true};var network = new vis.Network(container, data, options);</script></body></html>"
    };
}
