package inga.springpropertyloader;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface PropertyLoader {
    List<Map<String, Object>> getProperties();
    static PropertyLoader findLoader(Path path) {
        var lastIndex = path.getFileName().toString().lastIndexOf('.');
        if (lastIndex <= 0) {
            throw new IllegalArgumentException("no file extension");
        }
        return switch (path.getFileName().toString().substring(lastIndex + 1)) {
            case "properties" -> new PropertiesLoader(path);
            case "yml", "yaml" -> new YamlLoader(path);
            default -> throw new IllegalArgumentException("unsupported file extension");
        };
    }
}
