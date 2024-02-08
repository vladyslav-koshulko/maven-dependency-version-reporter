package org.example;

import java.io.File;
import java.nio.file.Path;
import javax.management.modelmbean.XMLParseException;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DependencyReporterImpl implements DependencyReporter {
    private String repo;

    private static final String POM = "./pom.xml";


    public DependencyReporterImpl(String repo) {
        this.repo = repo;
    }

    @Override
    public Map<Dependency, List<Dependency>> getDependencyVersions() {
        Model model = loadPom();
        Map<Dependency, List<Dependency>> dependencyListMap = extractDependencies(model);
        return null;
    }

//    private Map<Dependency, List<Dependency>> loadDependenciesData() {
//
//    }


    private Map<Dependency, List<Dependency>> extractDependencies(Model model) {
        List<Dependency> dependencies = model.getDependencies();
        if (dependencies == null) {
            dependencies = new ArrayList<>();
        }
        return dependencies.stream().collect(Collectors.toMap(
                dependency -> dependency,
                dependency -> List.of()));
    }


    private Model loadPom() {
        MavenXpp3Reader loader = new MavenXpp3Reader();
//        try {
            System.out.println(Path.of("."));
//            return loader.read(getPom());
            return null;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    private FileReader getPom() throws XMLParseException {
        try {
            return new FileReader(new File(POM));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
