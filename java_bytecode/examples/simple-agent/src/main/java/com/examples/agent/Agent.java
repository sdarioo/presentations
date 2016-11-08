package com.examples.agent;

import java.lang.instrument.Instrumentation;

public class Agent 
{
	public static void premain(String agentArgs, Instrumentation inst) {
		MyClassTransformer transformer = new MyClassTransformer();
		inst.addTransformer(transformer);
	}
}
