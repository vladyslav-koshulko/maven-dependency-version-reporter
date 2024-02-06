package org.example.Reporter;

import org.apache.maven.model.Dependency;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        String reducedVersions = versions.stream().map(Dependency::getVersion).reduce("\n", (s, s2) -> "\t" + s2 + "\n");
        String dependencyFullString = dependency.getGroupId() + "/" + dependency.getArtifactId() + ": " + dependency.getVersion();
        String out = dependencyFullString + reducedVersions;
        System.out.println(out+"\n");
    }
}
