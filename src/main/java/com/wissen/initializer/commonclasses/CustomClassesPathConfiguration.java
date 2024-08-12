package com.wissen.initializer.commonclasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import io.spring.initializr.generator.project.MutableProjectDescription;
import io.spring.initializr.generator.project.ProjectDescription;

@Configuration
public class CustomClassesPathConfiguration {

    @Autowired
    private CustomProperties customProperties;

    @Value("${package.name}")
    private String packageName;

    @Bean
    public ProjectDescription projectDescription() {
        MutableProjectDescription description = new MutableProjectDescription();
        description.setPackageName(packageName + ".util");
        return description;
    }

    @Bean
    public CustomClassProjectContributor customClassProjectContributor(ProjectDescription description) {
    	System.out.println(customProperties.getClassFilePaths());
        return new CustomClassProjectContributor(customProperties.getClassFilePaths(), description);
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
