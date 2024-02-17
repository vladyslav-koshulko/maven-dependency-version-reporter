package org.example;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.example.Reporter.ConsoleReporter;
import org.example.Reporter.Reporter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mojo(name = "report")
public class PluginReporter extends AbstractMojo {

    private final DependencyReporter dependencyReporter;

    private final DependencyExtractor dependencyExtractor;
    private final Reporter reporter;

    @Parameter(name = "repository", defaultValue = "https://search.maven.org/solrsearch/select?q=g:%s&a:%s&core=gav&rows=%d&wt=%s" )
    private String mavenRepository;
    @Parameter(name = "pomPath", defaultValue = "./pom.xml")
    private String pomPath;

    public PluginReporter() {
        dependencyReporter = new DependencyReporterImpl(pomPath);
        dependencyExtractor = new RemoteDependencyExtractor(mavenRepository);
        reporter = new ConsoleReporter();
    }

    @Override
    public void execute() {
        System.out.println("Version reporter init");
        reporter.printReport(getDependencies());
    }

    public Map<Dependency, List<Dependency>> getDependencies() {
        List<Dependency> dependencyVersions = dependencyReporter.getDependencyVersions();
        return dependencyVersions.stream().collect(Collectors.toMap(
                dependency -> dependency,
                dependencyExtractor::extract));
    }
}
