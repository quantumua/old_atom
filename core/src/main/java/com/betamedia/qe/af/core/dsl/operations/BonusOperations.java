package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.tp.api.model.Bonus;
import com.betamedia.tp.api.model.enums.BonusType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
public interface BonusOperations {

    Bonus get(String id);

    Bonus getByDisplayId(String displayId);

    Bonus addBonus(String accountId, BonusType bonusType, Double amount, Double wagerAmount);

}