package com.examples.bytecode;

public class Lambda {
	
	public static void main(String[] args) {
		lambdaExample().run();
	}
	
	public static Runnable lambdaExample() {
		return () -> {
			System.out.println("1111111");
		};
	}
	
//	private static void lambda$0() {
//	    System.out.println("222222");
//	}
}
