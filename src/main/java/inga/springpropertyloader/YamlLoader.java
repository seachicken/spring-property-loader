package inga.springpropertyloader;

import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.core.io.FileSystemResource;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class YamlLoader implements PropertyLoader {
    private final Path path;
    private final List<String> profileCandidates;

    public YamlLoader(Path path, List<String> profileCandidates) {
        this.path = path;
        this.profileCandidates = profileCandidates;
    }

    @Override
    public Map<String, Object> getProperties() {
        var processor = new YamlProcessor() {
            public Map<String, Object> getProperties() {
                AtomicReference<Map<String, Object>> result = new AtomicReference<>(new HashMap<>());
                process((properties, map) -> {
                    var onProfile = properties.getProperty("spring.config.activate.on-profile");
                    if (onProfile == null || profileCandidates.isEmpty() || profileCandidates.contains(onProfile)) {
                        result.set(getFlattenedMap(map));
                    }
                });
                return result.get();
            }
        };
        processor.setResources(new FileSystemResource(path));
        return processor.getProperties();
    }
}
