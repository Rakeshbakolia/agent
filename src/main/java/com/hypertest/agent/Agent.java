package com.hypertest.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String args, Instrumentation inst){
        if("REPLAY".equals(args)) {
            System.out.println("AGENT CALLING " + args);
            new AgentBuilder.Default()
                    .with(new AgentBuilder.InitializationStrategy.SelfInjection.Eager())
                    .type(ElementMatchers.isAnnotatedWith(RestController.class))
                    .or(ElementMatchers.isAnnotatedWith(Controller.class))
                    .transform(((builder, typeDescription, classLoader, javaModule, x) ->
                            builder.method(ElementMatchers.isAnnotatedWith(PostMapping.class))
                                    .intercept(MethodDelegation.to(RequestInterceptor.class))))
                    .installOn(inst);
        }
    }
}
