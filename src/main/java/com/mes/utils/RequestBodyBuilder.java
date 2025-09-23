package com.mes.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RequestBodyBuilder {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String fromYaml(String yamlPath, String section, Map<String, Object> overrides) {
        Yaml yaml = new Yaml();
        try (InputStream in = RequestBodyBuilder.class.getClassLoader().getResourceAsStream(yamlPath)) {
            if (in == null) {
                throw new RuntimeException("YAML file not found: " + yamlPath);
            }
            Map<String, Object> root = yaml.load(in);

            if (!root.containsKey(section)) {
                throw new RuntimeException("Section not found in YAML: " + section);
            }

            @SuppressWarnings("unchecked")
            Map<String, Object> body = new HashMap<>((Map<String, Object>) root.get(section));

            // Apply overrides
            if (overrides != null) {
                for (Map.Entry<String, Object> entry : overrides.entrySet()) {
                    applyNestedOverride(body, entry.getKey(), entry.getValue());
                }
            }

            return objectMapper.writeValueAsString(body);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read YAML or convert to JSON", e);
        }
    }

    // Hỗ trợ override nested key (ví dụ "address.city")
    @SuppressWarnings("unchecked")
    private static void applyNestedOverride(Map<String, Object> body, String keyPath, Object value) {
        String[] keys = keyPath.split("\\.");
        Map<String, Object> current = body;
        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];
            if (!(current.get(key) instanceof Map)) {
                current.put(key, new HashMap<String, Object>());
            }
            current = (Map<String, Object>) current.get(key);
        }
        current.put(keys[keys.length - 1], value);
    }

    public static String fromYaml(String yamlPath, String section) {
        return fromYaml(yamlPath, section, new HashMap<>());
    }

}
