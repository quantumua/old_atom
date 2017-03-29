package com.betamedia.qe.af.core.api.tp;

import com.betamedia.qe.af.core.api.tp.operations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
@Component
public class TPTemplateImpl implements TPTemplate {

    @Autowired
    private AccountGroupOperations accountGroupOperations;
    @Autowired
    private AccountOperations accountOperations;
    @Autowired
    private AssetOperations assetOperations;
    @Autowired
    private BonusOperations bonusOperations;
    @Autowired
    private BrandOperations brandOperations;
    @Autowired
    private FeedOperations feedOperations;
    @Autowired
    private OptionOperations optionOperations;
    @Autowired
    private OptionTemplateOperations optionTemplateOperations;
    @Autowired
    private OrderOperations orderOperations;
    @Autowired
    private PositionOperations positionOperations;
    @Autowired
    private SchedulerOperations schedulerOperations;
    @Autowired
    private TimezoneOperations timezoneOperations;
    @Autowired
    private TradingCalendarOperations tradingCalendarOperations;
    @Autowired
    private VolatilityUnitOperations volatilityUnitOperations;

    @Override
    public AccountOperations accountOperations() {
        return accountOperations;
    }

    @Override
    public AccountGroupOperations accountGroupOperations() {
        return accountGroupOperations;
    }

    @Override
    public AssetOperations assetOperations() {
        return assetOperations;
    }

    @Override
    public BonusOperations bonusOperations() {
        return bonusOperations;
    }

    @Override
    public BrandOperations brandOperations() {
        return brandOperations;
    }

    @Override
    public OptionOperations optionOperations() {
        return optionOperations;
    }

    @Override
    public OptionTemplateOperations optionTemplateOperations() {
        return optionTemplateOperations;
    }

    @Override
    public SchedulerOperations schedulerOperations() {
        return schedulerOperations;
    }

    @Override
    public TimezoneOperations timezoneOperations() {
        return timezoneOperations;
    }

    @Override
    public TradingCalendarOperations tradingCalendarOperations() {
        return tradingCalendarOperations;
    }

    @Override
    public VolatilityUnitOperations volatilityUnitOperations() {
        return volatilityUnitOperations;
    }

    @Override
    public FeedOperations feedOperations() {
        return feedOperations;
    }

    @Override
    public OrderOperations orderOperations() {
        return orderOperations;
    }

    @Override
    public PositionOperations positionOperations() {
        return positionOperations;
    }
}
