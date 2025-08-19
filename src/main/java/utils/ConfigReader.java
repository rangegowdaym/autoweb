package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties configProperties = new Properties();

    public static Properties loadProperties(String fileName) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config file: " + fileName, e);
        }
        return props;
    }

    public static void loadAllProperties(String... fileNames) {
        for (String fileName : fileNames) {
            try (FileInputStream fis = new FileInputStream(fileName)) {
                Properties tempProps = new Properties();
                tempProps.load(fis);
                configProperties.putAll(tempProps);
            } catch (FileNotFoundException e) {
                System.err.println("Config file not found: " + fileName);
            } catch (IOException e) {
                throw new RuntimeException("Could not load config file: " + fileName, e);
            }
        }
    }

    public static String getString(String key) {
        return configProperties.getProperty(key);
    }

    public static int getInt(String key) {
        String value = getString(key);
        if (value != null) {
            return Integer.parseInt(value);
        }
        throw new NumberFormatException("Property not found or not an integer: " + key);
    }

    public static boolean getBoolean(String key) {
        String value = getString(key);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        throw new IllegalArgumentException("Property not found or not a boolean: " + key);
    }

    public static long getLong(String key) {
        String value = getString(key);
        if (value != null) {
            return Long.parseLong(value);
        }
        throw new NumberFormatException("Property not found or not a long: " + key);
    }
}
