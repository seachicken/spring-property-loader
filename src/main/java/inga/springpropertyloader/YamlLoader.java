package inga.springpropertyloader;

import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.core.io.FileSystemResource;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class YamlLoader implements PropertyLoader {
    private final Path path;
    private final List<String> profileCandidates;
    private final Pattern importPathPattern = Pattern.compile("^classpath:(.+)$");
    private final Pattern resourcesPathPattern = Pattern.compile("^(.+/resources/).+$");
    private final Pattern propertyListPattern = Pattern.compile("^(.+)\\[[0-9]*\\]$");

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
                    var profile = properties.getProperty("spring.config.activate.on-profile");
                    if (profile == null) {
                        profile = properties.getProperty("spring.profiles");
                    }
                    if (profile == null || profileCandidates.isEmpty() || profileCandidates.contains(profile)) {
                        result.get().putAll(getFlattenedMap(map));
                    }

                    for (var importPath : YamlLoader.this.getProperties("spring.config.import", properties)) {
                        var matcher = importPathPattern.matcher(importPath);
                        var resourcesMatcher = resourcesPathPattern.matcher(YamlLoader.this.path.toString());
                        if (matcher.matches() && resourcesMatcher.matches()) {
                            result.get().putAll(
                                    new YamlLoader(Path.of(resourcesMatcher.group(1) + matcher.group(1)), YamlLoader.this.profileCandidates)
                                            .getProperties());
                        }
                    }
                });
                return result.get();
            }
        };
        processor.setResources(new FileSystemResource(path));
        return processor.getProperties();
    }

    private List<String> getProperties(String key, Properties properties) {
        var results = new ArrayList<String>();
        for (var pk : properties.keySet()) {
            if (pk.toString().equals(key)) {
                results.add(properties.get(pk).toString());
            } else {
                var matcher = propertyListPattern.matcher(pk.toString());
                if (matcher.matches() && matcher.group(1).equals(key)) {
                    results.add(properties.get(pk).toString());
                }
            }
        }
        return results;
    }
}
