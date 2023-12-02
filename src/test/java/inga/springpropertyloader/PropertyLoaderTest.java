package inga.springpropertyloader;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class PropertyLoaderTest {
    @Test
    void findPropertyPath() {
        var actual = PropertyLoader.findPropertyPaths(
                TestHelper.getFixturesPath("spring-tutorials/spring-boot-modules/spring-boot-properties")
        );
        assertThat(actual.stream().map(p -> Paths.get(System.getProperty("user.dir")).relativize(p)))
                .containsExactly(
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/extra.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/extra2.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/test/resources/additional-application-properties/application.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/test/resources/conversion.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/test/resources/configprops-test.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/test/resources/foo.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/test/resources/additional-application2.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/test/resources/database-test.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/test/resources/additional-application.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/test/resources/application.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/build.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/configprops.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/conversion.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/bar.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/foo.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/database.yml"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/child.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/parent.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/application.yml"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/application.properties"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/build.yml"),
                        Path.of("build/resources/test/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/database.properties")
                );
    }
}
