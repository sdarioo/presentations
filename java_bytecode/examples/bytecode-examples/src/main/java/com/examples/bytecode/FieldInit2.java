package com.examples.bytecode;

public class FieldInit2 extends Base {
	
	private String text = "Hello world";

	public FieldInit2() {
	}
	
	@Override
	public String getText() {
		return text;
	}
	
	public static void main(String[] args) {
		new FieldInit2();
	}
}

abstract class Base {
	
	Base() {
		System.out.println(getText().length());
	}
	
	public abstract String getText();
}
