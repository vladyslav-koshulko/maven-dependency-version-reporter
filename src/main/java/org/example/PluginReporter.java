package org.example;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;

import java.util.List;
import java.util.Map;

@Mojo(name = "report")
public class PluginReporter extends AbstractMojo {

    private DependencyReporter dependencyReporter;
    private static final String mavenCentralRepository = "https://search.maven.org/solrsearch/select?q=g:%s&a:%s&core=gav&rows=%d&wt=%s";

    public PluginReporter() {
        dependencyReporter = new DependencyReporterImpl(mavenCentralRepository);
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("Version reporter init");
        Map<Dependency, List<Dependency>> dependencyVersions = dependencyReporter.getDependencyVersions();
        dependencyVersions.entrySet().forEach(System.out::println);
    }
}
