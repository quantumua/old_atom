package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.connectors.tp.FWTPConnector;
import com.betamedia.tp.api.model.Position;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Oleksandr Losiev on 4/20/17.
 */
public class PositionOperationsTest {

    @InjectMocks
    private QAEnvPositionOperationsImpl positionOperations;

    @Mock
    private FWTPConnector tpConnector;

    private Position expectedPosition;
    private String positionId = "positionId";
    private String positionDisplayId = "positionDisplayId";
    private double positionAmount = 34;

    @BeforeClass
    public void setupClass() {
        expectedPosition = getExpectedPosition();
    }

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(tpConnector.readById(Position.class, positionId)).thenReturn(expectedPosition);
        when(tpConnector.readByDisplayId(Position.class, positionDisplayId)).thenReturn(expectedPosition);
    }

    @Test
    public void testGetPositionById() throws Exception {
        Position actualPosition = positionOperations.get(positionId);
        assertThat(expectedPosition, new ReflectionEquals(actualPosition));
    }

    @Test
    public void testGetPositionByDisplayId() throws Exception {
        Position actualPosition = positionOperations.getByDisplayId(positionDisplayId);
        assertThat(expectedPosition, new ReflectionEquals(actualPosition));
    }

    private Position getExpectedPosition() {
        Position position = new Position();
        position.setId(positionId);
        position.setDisplayId(positionDisplayId);
        position.setAmount(positionAmount);
        return position;
    }

}