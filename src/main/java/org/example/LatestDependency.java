package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.maven.model.Dependency;

public class LatestDependency {

  private final Map<Dependency, String> dependencies;

  public LatestDependency(List<Dependency> dependencies) {
    this.dependencies = setCurrentVersions(dependencies);
  }

  private static Map<Dependency, String> setCurrentVersions(List<Dependency> dependencies) {
    return dependencies.stream()
        .collect(Collectors.toMap(dependency -> dependency, Dependency::getVersion));
  }
}
