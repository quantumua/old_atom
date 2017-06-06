package com.betamedia.atom.core.api.tp.parsers.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Oleksandr Losiev on 4/14/17.
 */
public class CRMDataDeserializerTest {

    private final String fieldName = "id";
    private final int fieldValue = 4;

    private String validJson;
    private String invalidJson;
    private String arrayJson;

    private ObjectMapper mapper;
    private CRMDataDeserializer<Integer> deserializer;

    @BeforeClass
    public void setupClass() throws Exception{
        mapper = new ObjectMapper();
        deserializer = new CRMDataDeserializer<Integer>() {
            @Override
            protected Integer deserialize(JsonNode node) throws IOException {
                return node.get(fieldName).asInt();
            }
        };

        JSONObject validJsonObject = new JSONObject();
        validJsonObject.put(fieldName, fieldValue);

        validJson = validJsonObject.toString();
        invalidJson = "{{" + validJson;
        arrayJson = new JSONArray().put(validJsonObject).toString();
    }

    @Test
    public void testValidJson() throws Exception {
        JsonParser parser = mapper.getFactory().createParser(validJson);
        int result = deserializer.deserialize(parser, null);
        assertEquals(fieldValue, result);
    }

    @Test(expectedExceptions = IOException.class)
    public void testInvalidJson() throws Exception {
        JsonParser parser = mapper.getFactory().createParser(invalidJson);
        deserializer.deserialize(parser, null);
    }

    @Test
    public void testArrayNode() throws Exception {
        JsonParser parser = mapper.getFactory().createParser(arrayJson);
        Integer result = deserializer.deserialize(parser, null);
        assertNull(result);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullParser() throws Exception {
        deserializer.deserialize(null, null);
    }
}
