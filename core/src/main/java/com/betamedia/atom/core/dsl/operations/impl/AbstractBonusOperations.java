package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.api.tp.adapters.TPCRMHttpAdapter;
import com.betamedia.atom.core.api.tp.entities.response.CRMAddBonus;
import com.betamedia.atom.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.atom.core.dsl.operations.BonusOperations;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.Bonus;
import com.betamedia.tp.api.model.enums.BonusType;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.testng.Assert.assertTrue;

/**
 * This class is designed to facilitate the execution of common operations related to bonus.
 * It can be used as a "building block" when writing integration tests.
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 * @see Bonus
 */
public abstract class AbstractBonusOperations<T extends EnvironmentDependent> implements BonusOperations<T> {

    //TODO modify brand display ID retrieval mechanism after GigaSpaces removal
    private static final String STUB_BRAND_DISPLAY_ID = "stubDisplayId";

    private static final Logger logger = LogManager.getLogger(AbstractBonusOperations.class);

    @Autowired
    private TPCRMHttpAdapter<T> crmHttpAdapter;

    /**
     * A method to add bonus to a given CRM account.
     */
    @Override
    public Bonus addBonus(String accountId, BonusType bonusType, Double amount, Double wagerAmount) {
        TPCRMResponse<CRMAddBonus> addBonusResponse = crmHttpAdapter.addBonus(accountId, bonusType, amount, wagerAmount, STUB_BRAND_DISPLAY_ID);
        assertTrue(addBonusResponse.getErrors().isEmpty(),
                "Got errors while adding bonus for accountid = " + accountId + ", " + addBonusResponse.getErrors());
        String bonusDisplayId = addBonusResponse.getResult().getBonusDisplayId();
        throw new NotImplementedException("Can not retrieve Bonus because of GigaSpaces deprecation");
    }
}
