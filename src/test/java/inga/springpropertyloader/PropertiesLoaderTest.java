package inga.springpropertyloader;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PropertiesLoaderTest {
    @Test
    void getProperties() {
        var actual = new PropertiesLoader(TestHelper.getFixturesPath("application.properties"))
                .getProperties();
        assertThat(actual).isEqualTo(Map.of(
                "a", "value"
        ));
    }
}