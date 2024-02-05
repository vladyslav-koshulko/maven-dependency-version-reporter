package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Repository;
import org.json.JSONObject;

public class LatestDependency {

  private final Map<Dependency, List<String>> dependencies;

  public LatestDependency(List<Dependency> dependencies) {
    this.dependencies = setCurrentVersions(dependencies);
  }

  private static Map<Dependency, List<String>> setCurrentVersions(List<Dependency> dependencies) {
    return dependencies.stream()
        .collect(Collectors.toMap(dependency -> dependency, dependency -> List.of(dependency.getVersion())));
  }

//  private JSONObject sendRequest(Dependency dependency) {
//    Repository repository = new Repository();
//    repository.getUrl();
//  }

}
