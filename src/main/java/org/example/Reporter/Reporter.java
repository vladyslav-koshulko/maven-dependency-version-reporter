package org.example.Reporter;

import org.apache.maven.model.Dependency;

import java.util.List;
import java.util.Map;

public interface Reporter {
    void printReport(Map<Dependency, List<Dependency>> dependencies);
}
