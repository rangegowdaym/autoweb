package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON string", e);
        }
    }

    public static <T> T fromJson(File file, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(file, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON from file: " + file.getAbsolutePath(), e);
        }
    }

    public static void writeJsonToFile(Object object, String filePath) {
        try {
            OBJECT_MAPPER.writeValue(new File(filePath), object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write JSON to file: " + filePath, e);
        }
    }


}