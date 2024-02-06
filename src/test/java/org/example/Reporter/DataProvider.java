package org.example.Reporter;

import org.apache.maven.model.Dependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class DataProvider {

    private DataProvider() {
    }

    public static Dependency generateDependency(String groupId, String artifactId, String version) {
        Dependency dependency = new Dependency();
        dependency.setGroupId(groupId);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        return dependency;
    }


    public static List<Dependency> generateDependency(Dependency dependency, int number) {
        return IntStream.range(0, number)
                .mapToObj(i -> generateDependency(dependency))
                .collect(Collectors.toCollection(() -> new ArrayList<>(number)));
    }

    public static Map<Dependency, List<Dependency>> generateDependency(Dependency dependency, List<Dependency> dependencies) {
        return Map.of(dependency, dependencies);
    }

    private static Dependency generateDependency(Dependency dependency) {
        return generateDependency(dependency.getGroupId(), dependency.getArtifactId(), dependency.getVersion());
    }



}
