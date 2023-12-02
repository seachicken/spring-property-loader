package inga.springpropertyloader;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PropertiesLoaderTest {
    @Test
    void getProperties() {
        var actual = new PropertiesLoader(TestHelper.getFixturesPath(
                "spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/foo.properties"))
                .getProperties();
        assertThat(actual).containsExactly(Map.of(
                "key.something", "val"
        ));
    }
}