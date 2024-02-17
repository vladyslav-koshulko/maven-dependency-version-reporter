package org.example;

import org.apache.maven.model.Dependency;

import java.util.List;

public abstract class DefaultDependencyExtractor implements DependencyExtractor {
    @Override
    public List<Dependency> extract(Dependency dependency) {
        return List.of(dependency);
    }
}