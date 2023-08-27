package inga.springpropertyloader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Map<Path, List<Map<String, Object>>> propertyPathCache = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var mapper = new ObjectMapper();

        while (scanner.hasNextLine()) {
            Path path = Path.of(scanner.nextLine());
            List<Map<String, Object>> properties;
            if (propertyPathCache.containsKey(path)) {
                properties = propertyPathCache.get(path);
            } else {
                properties = PropertyLoader.findPropertyPaths(path)
                        .stream()
                        .flatMap(p -> PropertyLoader.findLoader(p).getProperties().stream())
                        .collect(Collectors.toList());
                propertyPathCache.put(path, properties);
            }
            try {
                var json = mapper.writeValueAsString(properties);
                System.out.println(json);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
