package com.concurrent.theadsafety;


public class LazyInit {
	
	private Object a = null;
	
	public Object getInstance() {
		if(a == null) {
			a = new Object();
		}
		return a;
	}
}
