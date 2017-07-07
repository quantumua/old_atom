package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.dsl.operations.TimezoneOperations;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.tp.api.model.scheduling.Timezone;
import com.betamedia.tp.api.model.scheduling.TradingCalendar;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by Oleksandr Losiev on 5/5/17.
 */
public class TradingCalendarOperationsTest {
    private static class QAEnvTradingCalendarOperationsImpl extends AbstractTradingCalendarOperations<QAEnvironment> {
        @Override
        public EnvironmentType getEnvironment() {
            return EnvironmentType.QA;
        }
    }

    @InjectMocks
    private QAEnvTradingCalendarOperationsImpl tradingCalendarOperations;

    @Mock
    private FWTPConnector tpConnector;

    @Mock
    private TimezoneOperations timezoneOperations;

    private TradingCalendar expectedTradingCalendar;
    private String id = "testId";
    private String displayId = "testDisplayId";

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(tpConnector.readById(TradingCalendar.class, id)).thenReturn(expectedTradingCalendar);
        when(tpConnector.readByDisplayId(TradingCalendar.class, displayId)).thenReturn(expectedTradingCalendar);
        when(timezoneOperations.get()).thenReturn(new Timezone());

        doAnswer(invocationOnMock -> {
            TradingCalendar calendar = invocationOnMock.getArgumentAt(0, TradingCalendar.class);
            calendar.setId(id);
            return calendar;
        }).when(tpConnector).create(any(TradingCalendar.class));

        expectedTradingCalendar = getExpectedTradingCalendar();
    }

    @Test
    public void testGetDefaultTradingCalendar() {
        TradingCalendar actualTradingCalendar = tradingCalendarOperations.get();
        assertThat(expectedTradingCalendar, new ReflectionEquals(actualTradingCalendar, "calendarSchedules"));
    }

    @Test
    public void testGetTradingCalendarById() {
        TradingCalendar actualTradingCalendar = tradingCalendarOperations.get(id);
        assertThat(expectedTradingCalendar, new ReflectionEquals(actualTradingCalendar, "calendarSchedules"));
    }

    @Test
    public void testGetTradingCalendarByDisplayId() {
        TradingCalendar actualTradingCalendar = tradingCalendarOperations.getByDisplayId(displayId);
        assertThat(expectedTradingCalendar, new ReflectionEquals(actualTradingCalendar, "calendarSchedules"));
    }

    private TradingCalendar getExpectedTradingCalendar() {
        TradingCalendar calendar = new TradingCalendar();
        Timezone timezone = timezoneOperations.get();
        calendar.setName("automation1");
        calendar.setTimezone(timezone);
        calendar.setTimezoneId(timezone.getId());
        calendar.setDescription("This is an auto generated Calendar by automation test");
        calendar.setId(id);
        return calendar;
    }
}
