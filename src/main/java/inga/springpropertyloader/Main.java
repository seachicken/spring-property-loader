package inga.springpropertyloader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Map<Input, List<Map<String, Object>>> propertyPathCache = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var mapper = new ObjectMapper();

        while (scanner.hasNextLine()) {
            Input input;
            try {
                input = mapper.readValue(scanner.nextLine(), Input.class);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            List<Map<String, Object>> properties;
            if (propertyPathCache.containsKey(input)) {
                properties = propertyPathCache.get(input);
            } else {
                properties = PropertyLoader.findPropertyPaths(input.from(), input.profiles())
                        .stream()
                        .map(p -> PropertyLoader.findLoader(p, input.profiles()).getProperties())
                        .collect(Collectors.toList());
                propertyPathCache.put(input, properties);
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
