package org.example;

import org.apache.maven.model.Dependency;

import java.util.List;

public interface DependencyReporter {

    List<Dependency> getDependencyVersions();
}
