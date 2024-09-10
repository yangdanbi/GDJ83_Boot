package com.db.app.Lamdas;

public class Service {
	
	public void act() throws Exception {
		Minus minus = new Minus();
		minus.calc(0, 0);
		//자기 스스로 Overriding
		Myfunction mf = (int n1, int n2) -> n1+n2;
		int result = mf.calc(3, 2);
	}

}
