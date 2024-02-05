package org.example;

import org.apache.maven.model.Dependency;

import java.util.List;
import java.util.Map;

public interface DependencyReporter {

    Map<Dependency, List<Dependency>> getDependencyVersions();
}
