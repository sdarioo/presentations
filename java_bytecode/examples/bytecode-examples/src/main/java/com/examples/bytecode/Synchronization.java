package com.examples.bytecode;

public class Synchronization {
	
	// About 13% faster than foo2
	public synchronized String foo1() {
		return "hello";
	}
	
	public String foo2() {
		synchronized (this) {
			return "hello";
		}
	}

}
