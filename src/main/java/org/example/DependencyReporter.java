package org.example;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Parent;

import java.util.List;

public abstract class DependencyReporter {

    protected String sourceLocation;


    public DependencyReporter() {

    }

    protected abstract Dependency getDependency(String groupId, String artifactId, String version);

    public abstract List<Dependency> gDependencies();

    public abstract String getLatestVersion(Dependency dependency);


}
