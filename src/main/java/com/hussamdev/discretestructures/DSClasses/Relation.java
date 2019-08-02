package com.hussamdev.discretestructures.DSClasses;

import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;

public class Relation {
	
	protected int domain[];
	protected int codomain[];
	protected boolean m[][];
	protected boolean ddef[];
	protected boolean range[];
	
	public Relation() {
		
	}
	
	public Relation(int domain[],int codomain[]) {
		this.domain = domain;
		ddef = new boolean[domain.length];
		
		this.codomain = codomain;
		range = new boolean[codomain.length];
		
		m = new boolean[domain.length][codomain.length];
	}
	
	public void setDomain(int domain[]) {
		
		this.domain = domain;
		ddef = new boolean[domain.length];
		
		try {
			m = new boolean[domain.length][codomain.length];
		}catch(Exception e) {
			
		}
		
	}
	
	public void setDomain(int a,int b) {
		
		domain = new int[b-a+1];
		
		for(int i=a,j=0;i<=b;i++,j++)
			domain[j] = i;
		ddef = new boolean[domain.length];
		try {
			m = new boolean[domain.length][codomain.length];
		}catch(Exception e) {
			
		}	}

	public int[] getDomain(){
		return domain;
	}
	public void setCodomain(int codomain[]) {
		this.codomain  = codomain;
		range = new boolean[codomain.length];
		try {
			m = new boolean[domain.length][codomain.length];
		}catch(Exception e) {
			
		}	}
	
	public void setCodomain(int a,int b) {
		codomain = new int[b-a+1];
		for(int i=a,j=0;i<=b;i++,j++)
			codomain[j] = i;
		range = new boolean[codomain.length];
		try {
			m = new boolean[domain.length][codomain.length];
		}catch(Exception e) {
			
		}	}

    public int[] getCodomain(){
        return codomain;
    }
	
	public String domainOfDef() {
		
		String str = "{";
		
		for(int i=0;i<ddef.length;i++)
			if(ddef[i])
				str+=domain[i]+",";
		
	    str = str.substring(0, str.length()-1);
	    str += "}";
				
		return str;
		
		
	}

	public String[] domainOfDef(int type)
	{
		String str = domainOfDef();
		str = str.substring(1,str.length()-1);

		return str.split(",");
	}
	
	public String range() {
		
		String str = "{";
		
		for(int i=0;i<range.length;i++)
			if(range[i])
				str+=codomain[i]+",";
		
	    str = str.substring(0, str.length()-1);
	    str += "}";
				
		return str;
		
	}

	public String[] range(int type) {

		String str = range();
		str = str.substring(1,str.length()-1);

		return str.split(",");
	}
	
	public int getPos(int a,int s[]) {
		int x = Arrays.binarySearch(s, a);
		if(x>=0)
			return x;
		return -1;
	}
	
	public boolean addElement(int x,int y) {
		
		x = getPos(x,domain);
		y = getPos(y,codomain);
		
		if(x==-1 || y==-1)
			return false;

		ddef[x] = true;
		range[y] = true;
		m[x][y] = true;
		
		return true;
	}

	public boolean removeElement(int x,int y){
		x = getPos(x,domain);
		y = getPos(y,codomain);

		if(x==-1 || y==-1)
			return false;

		int count=0;
		for(int i=0;i<ddef.length;i++)
		{
			if(ddef[x])
				count++;
			if(count == 1)
				ddef[x] = false;
		}

		count=0;
		for(int i=0;i<range.length;i++)
		{
			if(range[x])
				count++;
			if(count == 1)
				range[x] = false;
		}

		if(m[x][y] == false)
			return false;

		m[x][y] = false;
		return true;
	}
	
	public String toString() {

		String str = "{";
		for(int i=0;i<m.length;i++)
			for(int j=0;j<m[i].length;j++)
				if(m[i][j])
					str+="("+domain[i]+","+codomain[j]+"),";
		str+="}";

		int index = str.length()-2;
		Log.e("STRINGA",str.charAt(index)+"");
		if(str.charAt(index) == ',')
		{
			str=str.substring(0,str.length()-2);
			str+="}";
		}
		
		return str;
	}
}
