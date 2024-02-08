package org.example.Reporter;

import org.apache.maven.model.Dependency;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import static org.example.Reporter.DataProvider.generateDependency;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ConsoleReporterTest {

    private Reporter reporter = new ConsoleReporter();
    private PrintStream out = System.out;
    private StringBuffer buffer = new StringBuffer();


    @BeforeEach
    void before() {
        assert buffer.isEmpty();
        mockOutStream();
    }

    @AfterEach
    void after() {
        unmockOutStream();
        clearBuffer();
    }

    @Test
    void printReport() {
        // Given
        Dependency baseDependency = generateDependency("aaa", "bbb", "123-abc");
        List<Dependency> dependencies = generateDependency(baseDependency, 2);
        Map<Dependency, List<Dependency>> dependencyMap = generateDependency(baseDependency, dependencies);

        // When
        reporter.printReport(dependencyMap);

        assertEquals(buffer.toString(), ("aaa/bbb: 123-abc\n\t123-abc\n\t123-abc"));
    }


    private void mockOutStream() {
        System.setOut(getPrintStreamMock());
    }

    private PrintStream getPrintStreamMock() {
        return new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                buffer.append((char) b);
            }
        });
    }

    private void unmockOutStream() {
        System.setOut(out);
        System.out.println(buffer.toString());
    }

    private void clearBuffer() {
        buffer.delete(0, buffer.length());
    }
}
