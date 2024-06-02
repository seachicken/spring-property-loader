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
            var actual = new YamlLoader(TestHelper.getFixturesPath("yaml-on-profile/application.yml"), List.of("dev"))
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "spring.profiles.active[0]", "dev",
                    "spring.config.activate.on-profile", "dev",
                    "a", "value-1"
            ));
        }

        @Test
        void getPropertiesWithProdProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("yaml-on-profile/application.yml"), List.of("prod"))
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "spring.profiles.active[0]", "dev",
                    "spring.config.activate.on-profile", "prod",
                    "a", "value-2"
            ));
        }

        @Test
        void getPropertiesWithDefaultProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("yaml-on-profile/application.yml"), Collections.emptyList())
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "spring.profiles.active[0]", "dev",
                    "spring.config.activate.on-profile", "prod",
                    "a", "value-2"
            ));
        }
    }

    @Nested
    class ProfilesYaml {
        @Test
        void getPropertiesWithDevProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("yaml-profiles/application.yml"), List.of("dev"))
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "spring.profiles.active[0]", "dev",
                    "spring.profiles", "dev",
                    "a", "value-1"
            ));
        }

        @Test
        void getPropertiesWithProdProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("yaml-profiles/application.yml"), List.of("prod"))
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "spring.profiles.active[0]", "dev",
                    "spring.profiles", "prod",
                    "a", "value-2"
            ));
        }

        @Test
        void getPropertiesWithDefaultProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("yaml-profiles/application.yml"), Collections.emptyList())
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "spring.profiles.active[0]", "dev",
                    "spring.profiles", "prod",
                    "a", "value-2"
            ));
        }
    }

    @Nested
    class ProfileSuffixYaml {
        @Test
        void getDefaultPropertiesWithDevProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("yaml-profile-suffix/application.yml"), List.of("dev"))
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "a", "value-0"
            ));
        }

        @Test
        void getDevPropertiesWithDevProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("yaml-profile-suffix/application-dev.yml"), List.of("dev"))
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "a", "value-1"
            ));
        }

        @Test
        void getProdPropertiesWithDevProfile() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("yaml-profile-suffix/application-prod.yml"), List.of("dev"))
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "a", "value-2"
            ));
        }
    }

    @Nested
    class ImportYaml {
        @Test
        void getImportedProperties() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("yaml-import/application.yml"), Collections.emptyList())
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "a", "value-1",
                    "spring.config.import", "classpath:/fixtures/yaml-import/a.yml"
            ));
        }

        @Test
        void getImportedPropertiesWithMultipleImport() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("yaml-imports/application.yml"), Collections.emptyList())
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "a", "value-2",
                    "spring.config.import[0]", "classpath:/fixtures/yaml-imports/a.yml",
                    "spring.config.import[1]", "classpath:/fixtures/yaml-imports/b.yml"
            ));
        }

        @Test
        void getFlatImportedPropertiesWithMultipleImport() {
            var actual = new YamlLoader(TestHelper.getFixturesPath("yaml-imports-flat/application.yml"), Collections.emptyList())
                    .getProperties();
            assertThat(actual).isEqualTo(Map.of(
                    "a", "value-2",
                    "spring.config.import[0]", "classpath:/fixtures/yaml-imports-flat/a.yml",
                    "spring.config.import[1]", "classpath:/fixtures/yaml-imports-flat/b.yml"
            ));
        }
    }
}
