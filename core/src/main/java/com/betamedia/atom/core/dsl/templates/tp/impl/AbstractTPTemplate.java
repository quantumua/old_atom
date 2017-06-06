package com.betamedia.atom.core.dsl.templates.tp.impl;

import com.betamedia.atom.core.dsl.operations.*;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.dsl.templates.tp.TPTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public abstract class AbstractTPTemplate<T extends EnvironmentDependent> implements TPTemplate<T> {

    @Autowired
    private AccountGroupOperations<T> accountGroupOperations;
    @Autowired
    private AccountOperations<T> accountOperations;
    @Autowired
    private AssetOperations<T> assetOperations;
    @Autowired
    private BonusOperations<T> bonusOperations;
    @Autowired
    private BrandOperations<T> brandOperations;
    @Autowired
    private FeedOperations<T> feedOperations;
    @Autowired
    private OptionOperations<T> optionOperations;
    @Autowired
    private OptionTemplateOperations<T> optionTemplateOperations;
    @Autowired
    private OrderOperations<T> orderOperations;
    @Autowired
    private PositionOperations<T> positionOperations;
    @Autowired
    private SchedulerOperations<T> schedulerOperations;
    @Autowired
    private TimezoneOperations<T> timezoneOperations;
    @Autowired
    private TradingCalendarOperations<T> tradingCalendarOperations;
    @Autowired
    private VolatilityUnitOperations<T> volatilityUnitOperations;
    @Autowired
    private CustomerOperations<T> customerOperations;
    @Autowired
    private OnBoardingOperations<T> onBoardingOperations;

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

    @Override
    public CustomerOperations customerOperations() {
        return customerOperations;
    }

    @Override
    public OnBoardingOperations onBoardingOperations() {
        return onBoardingOperations;
    }


}
