package org.example;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DependencyReporterImpl implements DependencyReporter {
    private final String repo;

    private static final String POM = "./pom.xml";


    public DependencyReporterImpl(String repo) {
        this.repo = repo;
    }

    @Override
    public Map<Dependency, List<Dependency>> getDependencyVersions() {
        return extractDependencies(loadPom());
    }


    private Map<Dependency, List<Dependency>> extractDependencies(Model model) {
        return getNewListIfNull(model.getDependencies()).stream()
                .collect(Collectors.toMap(
                dependency -> dependency,
                dependency -> List.of()));
    }

    private List<Dependency> getNewListIfNull(List<Dependency> dependencies) {
        return dependencies != null ? dependencies : new ArrayList<>();
    }


    private Model loadPom() {
        MavenXpp3Reader loader = new MavenXpp3Reader();
        try {
            System.out.println(Path.of("."));
            return loader.read(getPom());
        } catch (IOException | XmlPullParserException e) {
            throw new RuntimeException(e);
        }
    }

    private FileReader getPom() {
        try {
            return new FileReader(POM);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
