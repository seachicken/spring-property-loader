package inga.springpropertyloader;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PropertiesLoaderTest {
    @Test
    void getProperties() {
        var actual = new PropertiesLoader(readFile("application.properties"))
                .getProperties();
        assertThat(actual).containsExactly(Map.of(
                "a", "1",
                "a.b", "2"
        ));
    }

    private Path readFile(String path) {
        return Path.of(getClass().getClassLoader().getResource("fixtures/" + path).getFile());
    }
}