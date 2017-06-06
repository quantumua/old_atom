package com.betamedia.atom.core.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/16/17.
 */
public class PropertiesUtils {

    /**
     * Permute properties that contain multiple comma delimited params(as single string) per key to the list of properties with single value for each key
     *
     * @param properties
     * @return
     */
    public static List<Properties> permute(Properties properties) {
        return cartesian(properties, properties.entrySet().iterator())
                .collect(Collectors.toList());
    }

    private static Stream<Properties> cartesian(Properties input, Iterator<Map.Entry<Object, Object>> iter) {
        if (!iter.hasNext()) {
            return Stream.of(new Properties());
        }
        Map.Entry<Object, Object> next = iter.next();
        Object k = next.getKey();
        List<String> values = StringUtils.parseCommaDelimitedString((String) next.getValue());
        Properties shallowCopy = new Properties();
        input.entrySet().stream()
                .filter(e -> !e.getKey().equals(k))
                .forEach(e -> shallowCopy.setProperty((String) e.getKey(), (String) e.getValue()));
        return values.stream()
                .flatMap(v -> cartesian(shallowCopy, shallowCopy.entrySet().iterator())
                        .peek(m -> m.put(k, v)));
    }

    public static Properties getProperties(MultipartFile uploadedProperties) throws IOException {
        Properties properties = new Properties();
        properties.load(uploadedProperties.getInputStream());
        return properties;
    }

    public static Properties getProperties(String fileName) throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find " + fileName);
            }
            //load a properties file from class path, inside static method
            prop.load(input);
            return prop;

        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
