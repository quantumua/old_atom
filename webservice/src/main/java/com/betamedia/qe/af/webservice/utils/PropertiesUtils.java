package com.betamedia.qe.af.webservice.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.betamedia.qe.af.webservice.utils.StringUtils.parseCommaDelimitedString;

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
        List<Properties> resultPropertiesList = new ArrayList<>();
        Set<Map.Entry<Object, Object>> singleValueSet = properties.entrySet();
        Properties multipleValuesProperty = new Properties();
        multipleValuesProperty.putAll(properties);
        Set<Map.Entry<Object, Object>> multipleValuesSet = multipleValuesProperty.entrySet().stream()
                .filter(entry -> parseCommaDelimitedString((String) entry.getValue())
                        .size() > 1).collect(Collectors.toSet());
        singleValueSet.removeAll(multipleValuesSet);
        for (Map.Entry entry : multipleValuesSet) {
            List<String> propertyValues = parseCommaDelimitedString((String) entry.getValue());
            for (String value : propertyValues) {
                update(resultPropertiesList, (String) entry.getKey(), value, singleValueSet);
            }
        }
        return resultPropertiesList;
    }

    private static void update(List<Properties> resultPropertiesList, String key, String value, Set<Map.Entry<Object, Object>> singleValueEntrySet) {
        if (resultPropertiesList.isEmpty()) {
            addNew(resultPropertiesList, key, value, singleValueEntrySet);
        } else {
            List<Properties> newProps = new ArrayList<>();
            Iterator<Properties> iter = resultPropertiesList.iterator();
            while (iter.hasNext()) {
                Properties prop = iter.next();
                if (prop.containsKey(key)) {
                    addNew(newProps, key, value, prop.entrySet());
                } else {
                    prop.put(key, value);
                }
            }
            resultPropertiesList.addAll(newProps);
        }
    }

    private static void addNew(List<Properties> resultPropertiesList, String key, String value, Set<Map.Entry<Object, Object>> initialProps) {
        Properties prop = new Properties();
        prop.putAll(initialProps.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        prop.put(key, value);
        resultPropertiesList.add(prop);
    }

    public static Properties getProperties(MultipartFile uploadedProperties) throws IOException {
        Properties properties = new Properties();
        properties.load(uploadedProperties.getInputStream());
        return properties;
    }
}
