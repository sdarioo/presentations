package com.examples.bytecode;

public class Lambda {
	
	public static void main(String[] args) {
		lambdaExample().run();
	}
	
	public static Runnable lambdaExample() {
		return () -> {
			System.out.println("Hello!");
		};
	}
	
	//private static void lambda$0() {
	//}
}
