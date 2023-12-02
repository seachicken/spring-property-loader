package inga.springpropertyloader;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class YamlLoaderTest {
    @Test
    void getPropertiesWhenProdProfile() {
        var actual = new YamlLoader(TestHelper.getFixturesPath("application.yml"), List.of("prod"))
                .getProperties();
        assertThat(actual).isEqualTo(Map.of(
                "spring.config.activate.on-profile", "prod",
                "a", "value-2"
        ));
    }

    @Test
    void getPropertiesWhenDefaultProfile() {
        var actual = new YamlLoader(TestHelper.getFixturesPath("application.yml"), Collections.emptyList())
                .getProperties();
        assertThat(actual).isEqualTo(Map.of(
                "spring.config.activate.on-profile", "prod",
                "a", "value-2"
        ));
    }
}