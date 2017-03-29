package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.common.utils.Time;
import com.betamedia.qe.af.common.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.api.tp.operations.TimezoneOperations;
import com.betamedia.qe.af.core.api.tp.operations.TradingCalendarOperations;
import com.betamedia.tp.api.model.scheduling.DayCalendarSchedule;
import com.betamedia.tp.api.model.scheduling.Timezone;
import com.betamedia.tp.api.model.scheduling.TradingCalendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.assertNotNull;

/**
 * Created by mbelyaev on 3/23/17.
 */
@Component
public class TradingCalendarOperationsImpl implements TradingCalendarOperations {
    private static final Logger logger = LogManager.getLogger(TradingCalendarOperationsImpl.class);

    @Autowired
    private AFTPConnector tpConnector;
    @Autowired
    private TimezoneOperations timezoneOperations;

    @Override
    public TradingCalendar get() {
        return create();
    }

    @Override
    public TradingCalendar get(String id) {
        TradingCalendar tradingCalendar = get(id, tpConnector::readById);
        assertNotNull(tradingCalendar, "TradingCalendar id=" + id + " is not available in GS");
        return tradingCalendar;
    }

    @Override
    public TradingCalendar getByDisplayId(String displayId) {
        TradingCalendar tradingCalendar = get(displayId, tpConnector::readByDisplayId);
        assertNotNull(tradingCalendar, "TradingCalendar displayId=" + displayId + " is not available in GS");
        return tradingCalendar;
    }

    private TradingCalendar get(String id, BiFunction<Class<TradingCalendar>, String, TradingCalendar> producer) {
        return producer.apply(TradingCalendar.class, id);
    }

    private TradingCalendar create() {
        TradingCalendar calendar = new TradingCalendar();
        Timezone timezone = timezoneOperations.get();
        calendar.setName("automation1");
        calendar.setTimezone(timezone);
        calendar.setTimezoneId(timezone.getId());
        calendar.setCalendarSchedules(createCalendarSchedules());
        calendar.setDescription("This is an auto generated Calendar by automation test");
        return tpConnector.create(calendar);
    }

    private static List<DayCalendarSchedule> createCalendarSchedules() {
        return Stream.of(
                new DayParams(7, 12, 12, 12, 44, true),
                new DayParams(1, 12, 12, 12, 55, true),
                new DayParams(2, 12, 12, 12, 44, true),
                new DayParams(3, 12, 12, 12, 44, true),
                new DayParams(4, 12, 12, 12, 44, true),
                new DayParams(5, 12, 12, 12, 55, true),
                new DayParams(6, 12, 12, 12, 44, true)
        )
                .map(TradingCalendarOperationsImpl::getSchedule)
                .collect(Collectors.toList());
    }

    private static class DayParams {
        final int dayIndex;
        final int startHour;
        final int startMinute;
        final int endHour;
        final int endMinute;
        final boolean isAllDay;

        DayParams(int dayIndex, int startHour, int startMinute, int endHour, int endMinute, boolean isAllDay) {
            this.dayIndex = dayIndex;
            this.startHour = startHour;
            this.startMinute = startMinute;
            this.endHour = endHour;
            this.endMinute = endMinute;
            this.isAllDay = isAllDay;
        }
    }

    private static DayCalendarSchedule getSchedule(DayParams params) {
        return new DayCalendarSchedule(params.isAllDay, new Time(params.startHour, params.startMinute), new Time(params.endHour, params.endMinute), params.dayIndex);
    }


}
