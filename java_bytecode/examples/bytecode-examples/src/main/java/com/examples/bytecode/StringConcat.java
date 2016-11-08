package com.examples.bytecode;

public class StringConcat {
	
	public String concat1(String s1, String s2, String s3) {
		return s1 + s2 + s3;
	}
	
	public String concat2(String s1, String s2, String s3) {
		return new StringBuilder(String.valueOf(s1)).append(s2).append(s3).toString();		
	}
}
