package org.example.Reporter;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.apache.maven.model.Dependency;

public class ConsoleReporter implements Reporter {

  private Comparator<Dependency> dependencyComparator;


  public ConsoleReporter() {
  }

  public ConsoleReporter(Comparator<Dependency> dependencyComparator) {
    this.dependencyComparator = dependencyComparator;
  }

  @Override
  public void printReport(Map<Dependency, List<Dependency>> dependencies) {
    for (Map.Entry<Dependency, List<Dependency>> dependencyListEntry : dependencies.entrySet()) {
      printDependencies(dependencyListEntry.getKey(), dependencyListEntry.getValue());
    }
  }


  private void printDependencies(Dependency dependency, List<Dependency> versions) {
    String reducedVersions = versions.stream().map(Dependency::getVersion)
        .reduce("", (s, s2) -> (s +"\n\t"+ s2));
    String dependencyFullString =
        dependency.getGroupId() + "/" + dependency.getArtifactId() + ": " + dependency.getVersion();
    String out = dependencyFullString +reducedVersions;
    System.out.print(out);
  }
}
