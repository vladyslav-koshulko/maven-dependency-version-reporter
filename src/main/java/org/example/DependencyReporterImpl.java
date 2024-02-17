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

public class DependencyReporterImpl implements DependencyReporter {

    protected String defaultPomPath = "./pom.xml";
    private final String pomPath;


    public DependencyReporterImpl(String pomPath) {
        this.pomPath = pomPath;
    }

    public DependencyReporterImpl() {
        this.pomPath = this.defaultPomPath;
    }

    @Override
    public List<Dependency> getDependencyVersions() {
        Model model = loadPom();
        return extractDependencies(model);
    }


    private List<Dependency> extractDependencies(Model model) {
        return new ArrayList<>(model.getDependencies());
    }


    private Model loadPom() {
        MavenXpp3Reader loader = new MavenXpp3Reader();
        try {
            System.out.println(Path.of("."));
            return loader.read(getPomPath());
        } catch (IOException | XmlPullParserException e) {
            throw new RuntimeException(e);
        }
    }

    private FileReader getPomPath() {
        try {
            return new FileReader(pomPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
