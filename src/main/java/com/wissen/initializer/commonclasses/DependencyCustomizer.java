package com.wissen.initializer.commonclasses;

import org.springframework.stereotype.Component;

import io.spring.initializr.generator.buildsystem.maven.MavenBuild;
import io.spring.initializr.generator.buildsystem.maven.MavenDependency;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

@Component
public class DependencyCustomizer implements BuildCustomizer<MavenBuild> {
    @Override
    public void customize(MavenBuild mavenBuild) {
    	MavenDependency dependency = MavenDependency.withCoordinates("org.springframework.boot", "spring-boot-starter-web").build();
    	mavenBuild.dependencies().add("spring-boot-starter-web", dependency);

    }
}