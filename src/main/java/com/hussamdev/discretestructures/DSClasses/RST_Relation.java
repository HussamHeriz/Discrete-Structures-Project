package com.hussamdev.discretestructures.DSClasses;

public class RST_Relation extends Relation {

	public RST_Relation(int set[]) {
		super(set,set);
	}
	
	public boolean isReflexive() {
		
		int length = m.length;
		for(int i=0;i<length;i++)
			if(!m[i][i])
				return false;
		
		return true;
	}
	
	public boolean isSymmetric() {
		
		for(int i=0;i<m.length;i++)
			for(int j=0;j<m[i].length;j++)
				if(m[i][j] && !m[j][i])
						return false;
		
		return true;
	}
	
	public boolean isTransitive() {
		for(int i=0;i<m.length;i++)
			for(int j=0;j<m.length;j++)
				for(int k=0;k<m.length;k++)
					if(m[i][j] && m[j][k] && !m[i][k])
						return false;
		return true;
	}
	
	public boolean isEquivalence() {
		return isReflexive()&&isSymmetric()&&isTransitive();
	}
	
	public String printEqClasses() {
		
		if(isEquivalence()) {
			
			String str = "";
			String strInternal = "";
			
			for(int i=0;i<m.length;i++) {
				str+="["+domain[i]+"]={";
				strInternal = "";
				for(int j=0;j<m.length;j++)
					if(m[i][j])
						strInternal+=domain[j]+",";
				strInternal = strInternal.substring(0, strInternal.length()-1);
				
				str+=strInternal+"}\n";
			}
			
			return str;
		}

		return "";
		
	}
}
