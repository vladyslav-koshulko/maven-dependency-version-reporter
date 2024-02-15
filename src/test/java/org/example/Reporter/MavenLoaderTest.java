package org.example.Reporter;

import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.junit.jupiter.api.Test;

import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MavenLoaderTest {
    private static final String POM = "./pom.xml";

    @Test
    void hasMavenLoadPom() {
        assertDoesNotThrow(() -> {
            assertNotNull(new MavenXpp3Reader().read(new FileReader(POM)));
        } );
    }
}
