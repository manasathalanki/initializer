package com.wissen.initializer.versionvalidation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.spring.initializr.generator.project.ProjectDescriptionCustomizer;

@Configuration
public class DependencyVersionValidationInitializerConfig {

	@Bean
	public ProjectDescriptionCustomizer javaVersionDependencyCustomizer() {
		return new JavaVersionDependencyCustomizer(dependencyMetadata());
	}

	@Bean
	public Map<String, Map<String, List<String>>> dependencyMetadata() {
		Map<String, Map<String, List<String>>> metadata = new HashMap<>();
		Map<String, List<String>> swaggerStarterVersions = new HashMap<>();
		swaggerStarterVersions.put("3.2.5", List.of("17"));
		metadata.put("wissen-swagger-starter", swaggerStarterVersions);
		Map<String, List<String>> errorhandlingStarter = new HashMap<>();
		errorhandlingStarter.put("3.0.0", List.of("17"));
		metadata.put("wissen-errorhandling-starter", errorhandlingStarter);
		return metadata;
	}
}
