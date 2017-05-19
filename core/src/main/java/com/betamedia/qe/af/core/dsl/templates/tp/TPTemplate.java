package com.betamedia.qe.af.core.dsl.templates.tp;

import com.betamedia.qe.af.core.dsl.templates.BackEndOperationsTemplate;
import com.betamedia.qe.af.core.dsl.operations.*;
import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public interface TPTemplate<T extends EnvironmentDependent> extends BackEndOperationsTemplate, EnvironmentDependent {

    AccountGroupOperations accountGroupOperations();

    AccountOperations accountOperations();

    AssetOperations assetOperations();

    BonusOperations bonusOperations();

    BrandOperations brandOperations();

    OptionOperations optionOperations();

    OptionTemplateOperations optionTemplateOperations();

    SchedulerOperations schedulerOperations();

    TimezoneOperations timezoneOperations();

    TradingCalendarOperations tradingCalendarOperations();

    VolatilityUnitOperations volatilityUnitOperations();

    FeedOperations feedOperations();

    OrderOperations orderOperations();

    PositionOperations positionOperations();

    CustomerOperations customerOperations();

    OnBoardingOperations onBoardingOperations();
}
