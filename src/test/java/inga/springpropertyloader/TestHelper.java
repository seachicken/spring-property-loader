package inga.springpropertyloader;

import java.nio.file.Path;

public class TestHelper {
    public static Path getFixturesPath(String path) {
        return Path.of(TestHelper.class.getClassLoader().getResource("fixtures/" + path).getFile());
    }
}
