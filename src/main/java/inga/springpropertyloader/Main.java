package inga.springpropertyloader;

import org.springframework.core.io.FileSystemResource;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var yamlLoader = new YamlLoader();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String path = scanner.nextLine();
            yamlLoader.setResources(new FileSystemResource(path));
            var properties = yamlLoader.getProperties();
            System.out.println(properties);
        }
    }
}
