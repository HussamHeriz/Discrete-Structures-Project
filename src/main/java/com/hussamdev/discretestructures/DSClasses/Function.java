package com.hussamdev.discretestructures.DSClasses;

public class Function extends Relation {
	
	protected boolean injectiveFlag;
	
	public Function() {
		
	}
	
	public Function(int domain[],int codomain[]) {
		super(domain,codomain);
	}
	
	public int getImage(int x) throws Exception{
		
		int index = getPos(x,domain);
		
		if(index == -1)
			throw new Exception("Value doesn't belong to domain");
		
		for(int j=0;j<codomain.length;j++)
			if(m[index][j])
				return codomain[j];
		
		throw new Exception("Value doesn't have an image");
	}
	
	public int getPreimage(int y) throws Exception {
	
		int index = getPos(y,codomain);
		
		if(index == -1)
			throw new Exception("Value doesn't belong to codomain");
		
		for(int i=0;i<codomain.length;i++)
			if(m[i][index])
				return domain[i];
		
		throw new Exception("Value doesn't have a preimage");
	}
	
	public boolean addElement(int x,int y) {
		
		x = getPos(x,domain);
		y = getPos(y,codomain);
		
		if(x==-1 || y==-1 || ddef[x])
			return false;
		
		ddef[x] = true;
		range[y] = true;
		m[x][y] = true;
		
		return true;
		
	}
	
	public boolean isLeftTotal() {
		
		for(int i=0;i<ddef.length;i++)
			if(!ddef[i])
				return false;
		
		return true;
	}
	
	public boolean isRightTotal() {
		
		for(int i=0;i<range.length;i++)
			if(!range[i])
				return false;
		
		return true;
	}
	
	public boolean isInjective() {
		
		for(int i=0;i<range.length;i++) {
			int count = 0;
			for(int j=0;j<ddef.length;j++)
				if(m[j][i])
					count++;
			if(count > 1)
				return false;
		}
			
		return true;
	}
	
	public boolean isSurjective() {
		return isRightTotal();
	}
	
	public boolean isBijective() {
		return isInjective() && isSurjective();
	}
	
	public Function composite(Function g) {
		
		Function f = new Function();
		
		for(int i=0;i<codomain.length;i++) {
			int x = this.codomain[i];
			if(g.getPos(x, g.domain) == -1)
				return null;
		}
		
		for(int i=0;i<g.domain.length;i++) {
			int x = g.domain[i];
			if(this.getPos(x, this.codomain) == -1)
				return null;
		}
			
		f.setDomain(this.domain);
		f.setCodomain(g.codomain);
		
		for(int i=0;i<this.domain.length;i++)
			for(int j=0;j<this.codomain.length;j++)
				if(this.m[i][j])
					for(int k=0;k<g.codomain.length;k++)
						if(g.m[i][k])
							f.addElement(this.domain[i], g.codomain[k]);
		return f;
	}
}
