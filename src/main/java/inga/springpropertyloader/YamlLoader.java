package inga.springpropertyloader;

import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.core.io.FileSystemResource;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YamlLoader implements PropertyLoader {
    private final Path path;

    public YamlLoader(Path path) {
        this.path = path;
    }

    @Override
    public List<Map<String, Object>> getProperties() {
        var processor = new YamlProcessor() {
            public List<Map<String, Object>> getProperties() {
                List<Map<String, Object>> result = new ArrayList<>();
                process((properties, map) -> result.add(getFlattenedMap(map)));
                return result;
            }
        };
        processor.setResources(new FileSystemResource(path));
        return processor.getProperties();
    }
}
