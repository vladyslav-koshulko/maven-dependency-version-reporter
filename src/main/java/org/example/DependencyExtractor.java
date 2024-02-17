package org.example;

import org.apache.maven.model.Dependency;

import java.util.List;

public interface DependencyExtractor {

    List<Dependency> extract(Dependency dependency);

}