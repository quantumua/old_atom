package com.betamedia.qe.af.core.dsl.templates.tp;

import com.betamedia.qe.af.core.dsl.templates.BackEndOperationsTemplate;
import com.betamedia.qe.af.core.dsl.operations.*;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public interface TPTemplate extends BackEndOperationsTemplate {

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
}
