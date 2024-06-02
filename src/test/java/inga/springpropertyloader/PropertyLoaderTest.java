package inga.springpropertyloader;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PropertyLoaderTest {
    @Test
    void findPropertyPath() {
        var actual = PropertyLoader.findPropertyPaths(
                TestHelper.getFixturesPath("spring-tutorials/spring-boot-modules/spring-boot-properties"),
                List.of("prod")
        );
        assertThat(actual.stream().map(p -> Paths.get(System.getProperty("user.dir")).relativize(p)))
                .containsExactly(
                        Path.of("src/test/resources/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/test/resources/additional-application-properties/application.properties"),
                        Path.of("src/test/resources/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/test/resources/application.properties"),
                        Path.of("src/test/resources/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/application.yml"),
                        Path.of("src/test/resources/fixtures/spring-tutorials/spring-boot-modules/spring-boot-properties/src/main/resources/application.properties")
                );
    }
}
