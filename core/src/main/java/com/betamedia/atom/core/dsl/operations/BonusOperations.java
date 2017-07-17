package com.betamedia.atom.core.dsl.operations;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.Bonus;
import com.betamedia.tp.api.model.enums.BonusType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
public interface BonusOperations <T extends EnvironmentDependent> extends EnvironmentDependent{

    Bonus addBonus(String accountId, BonusType bonusType, Double amount, Double wagerAmount);

}
