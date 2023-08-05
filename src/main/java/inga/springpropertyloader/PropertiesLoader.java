package inga.springpropertyloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesLoader implements PropertyLoader {
    private final Path path;

    public PropertiesLoader(Path path) {
        this.path = path;
    }

    public List<Map<String, Object>> getProperties() {
        var properties = new Properties();
        try (var is = Files.newInputStream(path)) {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return List.of(
                properties.entrySet().stream()
                        .collect(Collectors.toMap(
                                entry -> entry.getKey().toString(),
                                entry -> entry.getValue().toString()
                        ))
        );
    }
}
