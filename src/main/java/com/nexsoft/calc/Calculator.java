package com.nexsoft.calc;

public class Calculator {
	
	public int add(int a, int b) {
		return a + b;
	}
	
	public int susbtract(int a, int b) {
		return a - b;
	}
	
	public int multiply(int a, int b, int c) {
		return a * b * c;
	}
	
	public int divide(int a, int b) {
		return a / b;
	}
	
	public boolean isGanjil(int a) {
		return a % 2 == 1;
	}
	
	public boolean isVocal(String v) {
		if (v.equalsIgnoreCase("a") || v.equalsIgnoreCase("i") || v.equalsIgnoreCase("u") || v.equalsIgnoreCase("e") || v.equalsIgnoreCase("o")) return true;
		else return false;			
	}
}
