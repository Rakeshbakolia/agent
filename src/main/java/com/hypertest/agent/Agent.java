package com.hypertest.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String args, Instrumentation inst){
        System.out.println("AGENT CALLING");
//        new AgentBuilder.Default()
//                .type(ElementMatchers.any())
//                .transform((builder, type, classLoader, module, x) ->
//                        builder.method(ElementMatchers.any())
//                                .intercept(FixedValue.value("Application calling")))
//                .installOn(inst);
    }
}
