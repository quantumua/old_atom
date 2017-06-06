package com.betamedia.atom.core.connectors.tp;

import com.betamedia.atom.core.environment.tp.properties.SpacePropertiesHolder;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Created by vsnigur on 5/12/17.
 */
public class FWTPConnectorTest {

    private final String SPACE_LOCATORS = "SpaceLocators";
    private final String SPACE_USERNAME = "SpaceUsername";
    private final String SPACE_PASSWORD = "SpacePassword";
    private final String USERNAME = "Username";
    private final String PASSWORD = "Password";
    private final String SPACE_GROUPS = "SpaceGroups";
    private final String SPACE_URL = "SpaceURL";

    private FWTPConnector fwtpConnector;
    private SpacePropertiesHolder spacePropertiesHolder;

    @BeforeMethod
    public void setUp() throws Exception {
        spacePropertiesHolder = new SpacePropertiesHolder() {
            @Override
            public String getSpaceLocators() {
                return SPACE_LOCATORS;
            }

            @Override
            public String getSpaceUsername() {
                return SPACE_USERNAME;
            }

            @Override
            public String getSpacePassword() {
                return SPACE_PASSWORD;
            }

            @Override
            public String getSpaceGroups() {
                return SPACE_GROUPS;
            }

            @Override
            public String getSpaceURL() {
                return SPACE_URL;
            }

            @Override
            public String getUsername() {
                return USERNAME;
            }

            @Override
            public String getPwd() {
                return PASSWORD;
            }
        };
        fwtpConnector = Mockito.spy(FWTPConnector.class);
        ReflectionTestUtils.setField(fwtpConnector,
                FWTPConnector.class,
                "spacePropertiesHolder",
                spacePropertiesHolder,
                SpacePropertiesHolder.class);
    }

    @Test
    public void initProperties() {
        ReflectionTestUtils.invokeMethod(fwtpConnector,"initProperties");
        assertEquals(ReflectionTestUtils.getField(fwtpConnector,"spaceGroups"),SPACE_GROUPS);
        assertEquals(ReflectionTestUtils.getField(fwtpConnector,"spaceLocators"),SPACE_LOCATORS);
        assertEquals(ReflectionTestUtils.getField(fwtpConnector,"spaceUsername"),SPACE_USERNAME);
        assertEquals(ReflectionTestUtils.getField(fwtpConnector,"spacePassword"),SPACE_PASSWORD);
        assertEquals(ReflectionTestUtils.getField(fwtpConnector,"spaceUrl"),SPACE_URL);
    }

}