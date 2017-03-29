package com.betamedia.qe.af.core.api.tp.operations;

import com.betamedia.tp.api.model.scheduling.TradingCalendar;

/**
 * Created by mbelyaev on 3/23/17.
 */
public interface TradingCalendarOperations {
    TradingCalendar get();

    TradingCalendar get(String id);

    TradingCalendar getByDisplayId(String displayId);
}
