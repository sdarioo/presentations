package com.examples.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class MyClassTransformer implements ClassFileTransformer {

	public byte[] transform(ClassLoader loader, 
			String className, 
			Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, 
			byte[] classfileBuffer) throws IllegalClassFormatException {

		if (className != null) {
			System.out.println("Loading class: " + className + " (" + classLoaderName(loader) + ')');
		}
		return classfileBuffer;
	}
	
	private static String classLoaderName(ClassLoader loader) {
		return (loader == null) ? "bootstrap" : loader.toString();
	}

}
