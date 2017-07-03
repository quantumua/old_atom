package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.common.search.Page;
import com.betamedia.tp.api.model.volatility.VolatilityUnit;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Oleksandr Losiev on 5/5/17.
 */
public class VolatilityUnitOperationsTest {
    private static class QAEnvVolatilityUnitOperationsImpl extends AbstractVolatilityUnitOperations<QAEnvironment> implements QAEnvironment {}

    @InjectMocks
    private QAEnvVolatilityUnitOperationsImpl volatilityUnitOperations;

    @Mock
    private FWTPConnector tpConnector;

    @Mock
    private Page<VolatilityUnit> page;

    private VolatilityUnit expectedVolatilityUnit;
    private String assetId = "testAssetId";

    @BeforeClass
    public void setupClass() {
        expectedVolatilityUnit = getExpectedVolatilityUnit();
    }

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(tpConnector.create(any(VolatilityUnit.class))).then(returnsFirstArg());
        doReturn(page).when(tpConnector).readMultiple(any(), any(), any());
    }

    @Test
    public void testGenerateForAsset() {
        when(page.getContent()).thenReturn(Collections.emptyList());
        volatilityUnitOperations.generateForAsset(assetId, 0.2d, 2d, 1d, 1d);
        verify(tpConnector, times(168)).create(any(VolatilityUnit.class));
    }

    @Test
    public void testCreateForAsset() {
        List<VolatilityUnit> volatilityUnits = volatilityUnitOperations.createForAsset(assetId);
        assertTrue(volatilityUnits.size() == 1);
        assertThat(expectedVolatilityUnit, new ReflectionEquals(volatilityUnits.get(0), "day", "hour"));
    }

    @Test
    public void testGetVolatilityUnits() {
        List<VolatilityUnit> expectedUnits = new ArrayList<>();
        VolatilityUnit firstUnit = getExpectedVolatilityUnit();
        firstUnit.setDay(1);
        firstUnit.setHour(10);
        expectedUnits.add(firstUnit);
        VolatilityUnit secondUnit = getExpectedVolatilityUnit();
        secondUnit.setDay(2);
        secondUnit.setHour(6);
        expectedUnits.add(secondUnit);
        when(page.getContent()).thenReturn(expectedUnits);

        List<VolatilityUnit> actualUnits = volatilityUnitOperations.getVolatilityUnits(null);
        assertTrue(actualUnits.size() == 2);
        assertThat(firstUnit, new ReflectionEquals(actualUnits.get(0)));
        assertThat(secondUnit, new ReflectionEquals(actualUnits.get(1)));
    }

    private VolatilityUnit getExpectedVolatilityUnit() {
        VolatilityUnit volatilityUnit = new VolatilityUnit();
        volatilityUnit.setAssetId(assetId);
        volatilityUnit.setCreatedBy("Automatic test");
        volatilityUnit.setSigma(0.2d);
        volatilityUnit.setFactor(2d);
        volatilityUnit.setThreshold(1d);
        volatilityUnit.setRestriction99(1d);
        return volatilityUnit;
    }
}
