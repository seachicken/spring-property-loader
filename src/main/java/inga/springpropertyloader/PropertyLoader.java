package inga.springpropertyloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface PropertyLoader {
    Map<String, Object> getProperties();

    Pattern applicationPattern = Pattern.compile("^application\\..+$");
    Pattern profileSuffixPattern = Pattern.compile("^application-(.+?)\\..+$");

    static List<Path> findPropertyPaths(Path path, List<String> profileCandidates) {
        try (var stream = Files.walk(path)) {
            return stream
                    .filter(p -> p.toFile().isFile())
                    .filter(p -> {
                        var v = p.getFileName().toString();
                        return List.of("properties",
                                        "yml",
                                        "yaml"
                                )
                                .contains(v.substring(v.lastIndexOf('.') > 0 ? v.lastIndexOf('.') + 1 : 0));
                    })
                    .filter(p -> {
                        var suffixMatcher = profileSuffixPattern.matcher(p.getFileName().toString());
                        return applicationPattern.matcher(p.getFileName().toString()).matches()
                                || (suffixMatcher.matches() && profileCandidates.contains(suffixMatcher.group(1)));
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static PropertyLoader findLoader(Path path, List<String> profileCandidates) {
        var lastIndex = path.getFileName().toString().lastIndexOf('.');
        if (lastIndex <= 0) {
            throw new IllegalArgumentException("no file extension");
        }
        return switch (path.getFileName().toString().substring(lastIndex + 1)) {
            case "properties" -> new PropertiesLoader(path);
            case "yml", "yaml" -> new YamlLoader(path, profileCandidates);
            default -> throw new IllegalArgumentException("unsupported file extension");
        };
    }
}
