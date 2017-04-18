package com.betamedia.qe.af.core.api.tp.parsers.json.deserializer;

import com.betamedia.qe.af.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser.CRMDepositDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.betamedia.qe.af.core.api.tp.entities.response.CRMDeposit.TRANSACTION_ID;
import static org.testng.Assert.assertEquals;

/**
 * Created by Oleksandr Losiev on 4/14/17.
 */
public class CRMDepositDeserializerTest {

    private final String transactionId = "5";

    private String validJson;
    private String incompleteJson;

    private ObjectMapper mapper;
    private CRMDepositDeserializer deserializer;

    @BeforeMethod
    public void setup() throws Exception {
        mapper = new ObjectMapper();
        deserializer = new CRMDepositDeserializer();

        JSONObject incompleteJsonObject = new JSONObject();
        incompleteJson = incompleteJsonObject.toString();

        JSONObject validJsonObject = new JSONObject();
        validJsonObject.put(TRANSACTION_ID, transactionId);
        validJson = validJsonObject.toString();
    }

    @Test
    public void testValidJson() throws Exception {
        JsonParser parser = mapper.getFactory().createParser(validJson);
        Object result = deserializer.deserialize(parser, null);
        CRMDeposit crmDeposit = (CRMDeposit)result;

        assertEquals(transactionId, crmDeposit.getTransactionId());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testIncompleteJson() throws Exception {
        JsonParser parser = mapper.getFactory().createParser(incompleteJson);
        deserializer.deserialize(parser, null);
    }
}
