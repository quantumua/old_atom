package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.scheduling.TradingCalendar;

/**
 * Created by mbelyaev on 3/23/17.
 */
public interface TradingCalendarOperations<T extends EnvironmentDependent> extends EnvironmentDependent {
    TradingCalendar get();

    TradingCalendar get(String id);

    TradingCalendar getByDisplayId(String displayId);
}
