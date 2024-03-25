package com.hypertest.agent;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.stereotype.Component.class))

public class AgentApplication {

	public static void main(String[] args) {

	}

}
