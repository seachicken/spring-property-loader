package inga.springpropertyloader;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class YamlLoaderTest {
    @Nested
    class OnProfileYaml {
        @Test
        void getPropertiesWithDevProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("application-on-profile.yml"), List.of("dev"))
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "spring.config.activate.on-profile", "dev",
                    "a", "value-1"
            ));
        }

        @Test
        void getPropertiesWithProdProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("application-on-profile.yml"), List.of("prod"))
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "spring.config.activate.on-profile", "prod",
                    "a", "value-2"
            ));
        }

        @Test
        void getPropertiesWithDefaultProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("application-on-profile.yml"), Collections.emptyList())
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "spring.config.activate.on-profile", "prod",
                    "a", "value-2"
            ));
        }
    }

    @Nested
    class ProfilesYaml {
        @Test
        void getPropertiesWithDevProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("application-profiles.yml"), List.of("dev"))
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "spring.profiles", "dev",
                    "a", "value-1"
            ));
        }

        @Test
        void getPropertiesWithProdProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("application-profiles.yml"), List.of("prod"))
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "spring.profiles", "prod",
                    "a", "value-2"
            ));
        }

        @Test
        void getPropertiesWithDefaultProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("application-profiles.yml"), Collections.emptyList())
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "spring.profiles", "prod",
                    "a", "value-2"
            ));
        }
    }
}