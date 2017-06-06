package com.betamedia.atom.core.api.tp.entities.response;

import com.betamedia.atom.core.api.tp.parsers.json.deserializer.CRMRegisterResultDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Maksym Tsybulskyy
 *         Date: 10/5/16.
 */
@JsonDeserialize(using = CRMRegisterResultDeserializer.class)
public class CRMRegisterResult {

    private CRMCustomer customer;

    public CRMRegisterResult(CRMCustomer customer) {
        this.customer = customer;
    }

    public CRMCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(CRMCustomer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CRMRegisterResult{" +
                "customer=" + customer +
                '}';
    }
}
