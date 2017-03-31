package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.qe.af.common.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.api.tp.adapters.TPCRMHttpAdapter;
import com.betamedia.qe.af.core.api.tp.entities.response.AddBonus;
import com.betamedia.qe.af.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.qe.af.core.api.tp.operations.BonusOperations;
import com.betamedia.tp.api.model.Bonus;
import com.betamedia.tp.api.model.enums.BonusType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
@Component
public class BonusOperationsImpl implements BonusOperations {

    private static final Logger logger = LogManager.getLogger(BonusOperationsImpl.class);

    @Autowired
    private AFTPConnector tpConnector;

    @Autowired
    private TPCRMHttpAdapter crmHttpAdapter;

    @Override
    public Bonus get(String id) {
        Bonus bonus = tpConnector.readById(Bonus.class, id);
        assertNotNull(bonus, "Bonus with id=" + id + " is not available in GS");
        return bonus;
    }

    @Override
    public Bonus getByDisplayId(String displayedId) {
        Bonus bonus = tpConnector.readByDisplayId(Bonus.class, displayedId);
        assertNotNull(bonus, "Bonus with displayId=" + displayedId + " is not available in GS");
        return bonus;
    }

    @Override
    public Bonus addBonus(String accountId, BonusType bonusType, Double amount, Double wagerAmount) {
        TPCRMResponse<AddBonus> addBonusResponse = crmHttpAdapter.addBonus(accountId, bonusType, amount, wagerAmount);
        assertTrue(addBonusResponse.getErrors().isEmpty(),
                "Got errors while adding bonus for accountid = " + accountId + ", " + addBonusResponse.getErrors());
        String bonusDisplayId = addBonusResponse.getResult().getBonusDisplayId();
        Bonus bonus = getByDisplayId(bonusDisplayId);
        assertNotNull(bonus);
        assertEquals(bonus.getAmount(), amount);
        logger.info("The bonus for account {} is added, {}", accountId, bonus);
        return bonus;


    }
}
