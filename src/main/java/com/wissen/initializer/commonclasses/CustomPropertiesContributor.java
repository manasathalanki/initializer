package com.wissen.initializer.commonclasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import io.spring.initializr.generator.project.contributor.ProjectContributor;

@Component
public class CustomPropertiesContributor implements ProjectContributor {

	@Override
	public void contribute(Path projectRoot) throws IOException {
		Path propertiesFile = projectRoot.resolve("src/main/resources/application.properties");
		Files.createDirectories(propertiesFile.getParent());

		Path templatePath = new ClassPathResource("templates/application-custom.properties").getFile().toPath();
		List<String> lines = Files.readAllLines(templatePath);

		Files.write(propertiesFile, lines);
	}
}
