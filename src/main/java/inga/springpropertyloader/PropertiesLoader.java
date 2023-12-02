package inga.springpropertyloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesLoader implements PropertyLoader {
    private final Path path;

    public PropertiesLoader(Path path) {
        this.path = path;
    }

    public Map<String, Object> getProperties() {
        var properties = new Properties();
        try (var in = Files.newInputStream(path)) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().toString(),
                        entry -> entry.getValue().toString()));
    }
}
