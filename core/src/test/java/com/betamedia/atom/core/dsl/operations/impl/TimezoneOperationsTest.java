package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.tp.api.model.scheduling.Timezone;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Oleksandr Losiev on 5/5/17.
 */
public class TimezoneOperationsTest {
    private static class QAEnvTimezoneOperationsImpl extends AbstractTimezoneOperations<QAEnvironment> implements QAEnvironment {}

    @InjectMocks
    private QAEnvTimezoneOperationsImpl timezoneOperations;

    @Mock
    private FWTPConnector tpConnector;

    private Timezone expectedTimezone;
    private String id = "testId";

    @BeforeClass
    public void setupClass() {
        expectedTimezone = getExpectedTimezone();
    }

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(tpConnector.create(any(Timezone.class))).thenReturn(expectedTimezone);
        when(tpConnector.readById(Timezone.class, id)).thenReturn(expectedTimezone);
    }

    @Test
    public void testGetDefaultTimezone() {
        Timezone actualTimezone = timezoneOperations.get();
        assertThat(expectedTimezone, new ReflectionEquals(actualTimezone));
    }

    @Test
    public void testGetTimezoneById() {
        Timezone actualTimezone = timezoneOperations.get(id);
        assertThat(expectedTimezone, new ReflectionEquals(actualTimezone));
    }

    private Timezone getExpectedTimezone() {
        Timezone timezone = new Timezone();
        timezone.setId(id);
        return timezone;
    }
}
