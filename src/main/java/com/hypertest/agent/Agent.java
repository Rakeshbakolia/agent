package com.hypertest.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Agent {
    public static void premain(String args, Instrumentation inst){
        if("REPLAY".equals(args)) {
            System.out.println("AGENT CALLING " + args);
            new AgentBuilder.Default()
                    .with(new AgentBuilder.InitializationStrategy.SelfInjection.Eager())
                    .type(ElementMatchers.any())
                    .transform(((builder, typeDescription, classLoader, javaModule, x) ->
                            builder.method(named("createPost"))
                                    .intercept(Advice.to(TimerAdvice.class))))
                    .installOn(inst);
        }
    }
}
