package com.db.app.Lamdas;

public class Plus implements Myfunction {
	@Override
	public int calc(int num1, int num2) throws Exception {
		int result = num1+num2;
		
		return result;
	}
}
