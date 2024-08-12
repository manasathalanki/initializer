package com.wissen.initializer.commonclasses;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import io.spring.initializr.generator.project.contributor.ProjectContributor;

@Component
public class DockerfileProjectContributor implements ProjectContributor {

	@Value("classpath:templates/Dockerfile")
	private Resource dockerfileTemplate;

	@Override
	public void contribute(Path projectRoot) throws IOException {
		Path dockerfilePath = projectRoot.resolve("Dockerfile");
		try (InputStream inputStream = dockerfileTemplate.getInputStream()) {
			Files.copy(inputStream, dockerfilePath, StandardCopyOption.REPLACE_EXISTING);
		}
	}
}
