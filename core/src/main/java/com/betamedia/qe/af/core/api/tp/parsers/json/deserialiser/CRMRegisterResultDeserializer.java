package com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser;

import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMRegisterResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author Maksym Tsybulskyy
 *         Date: 10/12/16.
 */
public class CRMRegisterResultDeserializer extends CRMDataDeserializer<CRMRegisterResult> {

    public static final String CUSTOMER_NODE_KEY = "Customer";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected CRMRegisterResult deserialize(JsonNode node) throws IOException {

        return new CRMRegisterResult(getCRMCustomer(node));
    }

    private CRMCustomer getCRMCustomer(JsonNode node) throws IOException {
        JsonNode crmCustomerNode = node.get(CUSTOMER_NODE_KEY);
        return objectMapper.treeToValue(crmCustomerNode, CRMCustomer.class);
    }
}
