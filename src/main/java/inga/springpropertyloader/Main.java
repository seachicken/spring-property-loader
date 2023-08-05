package inga.springpropertyloader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var mapper = new ObjectMapper();

        while (scanner.hasNextLine()) {
            String path = scanner.nextLine();
            var properties = PropertyLoader
                    .findLoader(Path.of(path))
                    .getProperties();
            try {
                var json = mapper.writeValueAsString(properties);
                System.out.println(json);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
