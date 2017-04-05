package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/31/17.
 */
public interface CustomerOperations {

    CRMCustomer register();

    CRMCustomer register(CustomerBuilder customerBuilder);

}
