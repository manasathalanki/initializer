package com.wissen.initializer.commonclasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CustomProperties {

    private final Environment environment;
    private Map<String, String> classFilePaths = new HashMap<>();

    @Autowired
    public CustomProperties(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        // Load custom.classFilePaths properties into the map
        MutablePropertySources propertySources = ((StandardEnvironment) environment).getPropertySources();
        for (PropertySource<?> propertySource : propertySources) {
            if (propertySource instanceof MapPropertySource) {
                Map<String, Object> source = ((MapPropertySource) propertySource).getSource();
                source.forEach((key, value) -> {
                    if (key.startsWith("custom.classFilePaths.")) {
                        String resolvedValue = environment.resolvePlaceholders(value.toString());
                        String className = key.substring("custom.classFilePaths.".length());
                        classFilePaths.put(className, resolvedValue);
                    }
                });
            }
        }
    }

    public Map<String, String> getClassFilePaths() {
        return classFilePaths;
    }
}
