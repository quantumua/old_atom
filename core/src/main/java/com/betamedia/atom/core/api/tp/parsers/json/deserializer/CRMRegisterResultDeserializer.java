package com.betamedia.atom.core.api.tp.parsers.json.deserializer;

import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.api.tp.entities.response.CRMRegisterResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

/**
 * This class is used to deserialize a json node into CRMRegisterResult class.
 * It should be used via the {@link JsonDeserialize} annotation.
 *
 * @author Maksym Tsybulskyy
 *         Date: 10/12/16.
 * @see CRMDataDeserializer
 * @see CRMRegisterResult
 */
public class CRMRegisterResultDeserializer extends CRMDataDeserializer<CRMRegisterResult> {

    public static final String CUSTOMER_NODE_KEY = "Customer";
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Template method hook for node deserialization
     * @param node a json node, which should not be an array
     * @return deserialization result
     * @throws IOException
     */
    @Override
    protected CRMRegisterResult deserialize(JsonNode node) throws IOException {

        return new CRMRegisterResult(getCRMCustomer(node));
    }

    private CRMCustomer getCRMCustomer(JsonNode node) throws IOException {
        JsonNode crmCustomerNode = node.get(CUSTOMER_NODE_KEY);
        return objectMapper.treeToValue(crmCustomerNode, CRMCustomer.class);
    }
}
