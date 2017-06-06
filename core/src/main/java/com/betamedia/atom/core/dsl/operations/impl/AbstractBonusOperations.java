package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.api.tp.adapters.TPCRMHttpAdapter;
import com.betamedia.atom.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.dsl.operations.BrandOperations;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.api.tp.entities.response.CRMAddBonus;
import com.betamedia.atom.core.dsl.operations.BonusOperations;
import com.betamedia.tp.api.model.Bonus;
import com.betamedia.tp.api.model.enums.BonusType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.testng.Assert.*;

/**
 * This class is designed to facilitate the execution of common operations related to bonus.
 * It can be used as a "building block" when writing integration tests.
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 * @see Bonus
 */
public abstract class AbstractBonusOperations<T extends EnvironmentDependent> implements BonusOperations<T> {

    private static final Logger logger = LogManager.getLogger(AbstractBonusOperations.class);

    @Autowired
    private FWTPConnector<T> tpConnector;

    @Autowired
    private TPCRMHttpAdapter<T> crmHttpAdapter;

    @Autowired
    private BrandOperations<T> brandOperations;

    /**
     * A method to get bonus by id.
     */
    @Override
    public Bonus get(String id) {
        Bonus bonus = tpConnector.readById(Bonus.class, id);
        assertNotNull(bonus, "Bonus with id=" + id + " is not available in GS");
        return bonus;
    }

    /**
     * A method to get bonus by display id
     */
    @Override
    public Bonus getByDisplayId(String displayedId) {
        Bonus bonus = tpConnector.readByDisplayId(Bonus.class, displayedId);
        assertNotNull(bonus, "Bonus with displayId=" + displayedId + " is not available in GS");
        return bonus;
    }

    /**
     * A method to add bonus to a given CRM account.
     */
    @Override
    public Bonus addBonus(String accountId, BonusType bonusType, Double amount, Double wagerAmount) {
        TPCRMResponse<CRMAddBonus> addBonusResponse = crmHttpAdapter.addBonus(accountId, bonusType, amount, wagerAmount, brandOperations.get().getDisplayId());
        assertTrue(addBonusResponse.getErrors().isEmpty(),
                "Got errors while adding bonus for accountid = " + accountId + ", " + addBonusResponse.getErrors());
        String bonusDisplayId = addBonusResponse.getResult().getBonusDisplayId();
        Bonus bonus = getByDisplayId(bonusDisplayId);
        assertNotNull(bonus);
        assertEquals(bonus.getAmount(), amount);
        logger.info("The bonus for account {} is added, {}, {}", accountId, bonus, getEnvironment());
        return bonus;


    }
}
