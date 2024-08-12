package com.wissen.initializer.commonclasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

@Component
public class CustomClassProjectContributor implements ProjectContributor {
    private final Map<String, String> classFilePaths;
    private final ProjectDescription description;

    public CustomClassProjectContributor(Map<String, String> classFilePaths, ProjectDescription description) {
        this.classFilePaths = classFilePaths;
        this.description = description;
    }

    @Override
    public void contribute(Path projectRoot) throws IOException {
        String packageName = description.getPackageName();
        for (Map.Entry<String, String> entry : classFilePaths.entrySet()) {
            String className = entry.getKey();
            String classFilePath = entry.getValue();

            Path targetPath = projectRoot
                    .resolve("src/main/java/" + packageName.replace('.', '/') + "/" + className + ".java");
            Files.createDirectories(targetPath.getParent());

            Path sourcePath = Paths.get(classFilePath);
            List<String> lines = Files.readAllLines(sourcePath);
            String packageDeclaration = "package " + packageName + ";";

            if (!lines.isEmpty() && lines.get(0).startsWith("package ")) {
                lines.set(0, packageDeclaration);
            } else {
                lines.add(0, packageDeclaration);
            }

            Files.write(targetPath, lines);
        }
    }
}
