package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.qe.af.common.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.api.tp.adapters.CRMHTTPAdapter;
import com.betamedia.qe.af.core.api.tp.operations.BonusOperations;
import com.betamedia.tp.api.model.Bonus;
import com.betamedia.tp.api.model.enums.BonusType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

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
    private CRMHTTPAdapter crmHttpAdapter;

    @Override
    public Bonus get(String id) {
        Bonus bonus = tpConnector.readById(Bonus.class, id);
        assertNotNull(bonus, "Bonus with id=" + id + " is not available in GS");
        return bonus;
    }

    @Override
    public Bonus getByDisplaydId(String displayedId) {
        Bonus bonus = tpConnector.readByDisplayId(Bonus.class, displayedId);
        assertNotNull(bonus, "Bonus with displayId=" + displayedId + " is not available in GS");
        return bonus;
    }

    @Override
    public Bonus addBonus(String accountId, BonusType bonusType, Double amount, Double wagerAmount) {
        String bonusDisplayId = crmHttpAdapter.addBonus(accountId, bonusType, amount, wagerAmount);
        Bonus bonus = getByDisplaydId(bonusDisplayId);
        assertNotNull(bonus);
        assertEquals(bonus.getAmount(), Double.valueOf(amount));
        logger.info("The bonus for account {} is added, {}", accountId, bonus);
        return bonus;


    }
}
