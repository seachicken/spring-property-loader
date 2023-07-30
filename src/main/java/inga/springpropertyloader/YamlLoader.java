package inga.springpropertyloader;

import org.springframework.beans.factory.config.YamlProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YamlLoader extends YamlProcessor {
    public List<Map<String, Object>> getProperties() {
        List<Map<String, Object>> result = new ArrayList<>();
        process((properties, map) -> result.add(getFlattenedMap(map)));
        return result;
    }
}
