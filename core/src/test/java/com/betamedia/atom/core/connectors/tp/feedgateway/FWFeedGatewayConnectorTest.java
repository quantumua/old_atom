package com.betamedia.atom.core.connectors.tp.feedgateway;

import com.betamedia.atom.core.environment.tp.properties.FeedGatewayPropertiesHolder;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * Created by vsnigur on 5/11/17.
 */
public class FWFeedGatewayConnectorTest {

    private final String GW_SPACE_LOCATORS = "GWSpaceLocators";
    private final String GW_SPACE_USERNAME = "GWSpaceUsername";
    private final String GW_SPACE_PASSWORD = "GWSpacePassword";
    private final String GW_SPACE_GROUPS = "GWSpaceGroups";
    private final String GW_SPACE_URL = "GWSpaceURL";

    private FeedGatewayPropertiesHolder feedGatewayPropertiesHolder;
    private FWFeedGatewayConnector fwFeedGatewayConnector;

    @BeforeMethod
    public void setUp() {
        feedGatewayPropertiesHolder = new FeedGatewayPropertiesHolder() {
            @Override
            public String getGWSpaceLocators() {
                return GW_SPACE_LOCATORS;
            }

            @Override
            public String getGWSpaceUsername() {
                return GW_SPACE_USERNAME;
            }

            @Override
            public String getGWSpacePassword() {
                return GW_SPACE_PASSWORD;
            }

            @Override
            public String getGWSpaceGroups() {
                return GW_SPACE_GROUPS;
            }

            @Override
            public String getGWSpaceURL() {
                return GW_SPACE_URL;
            }
        };
        fwFeedGatewayConnector = Mockito.spy(FWFeedGatewayConnector.class);
        ReflectionTestUtils.setField(fwFeedGatewayConnector,
                FWFeedGatewayConnector.class,
                "feedGatewayPropertiesHolder",
                feedGatewayPropertiesHolder,
                FeedGatewayPropertiesHolder.class);
    }

    @Test
    public void initProperties() throws Exception {
        ReflectionTestUtils.invokeMethod(fwFeedGatewayConnector,"initProperties");
        assertEquals(ReflectionTestUtils.getField(fwFeedGatewayConnector,"spaceGroups"),GW_SPACE_GROUPS);
        assertEquals(ReflectionTestUtils.getField(fwFeedGatewayConnector,"spaceLocators"),GW_SPACE_LOCATORS);
        assertEquals(ReflectionTestUtils.getField(fwFeedGatewayConnector,"spaceUsername"),GW_SPACE_USERNAME);
        assertEquals(ReflectionTestUtils.getField(fwFeedGatewayConnector,"spacePassword"),GW_SPACE_PASSWORD);
        assertEquals(ReflectionTestUtils.getField(fwFeedGatewayConnector,"spaceUrl"),GW_SPACE_URL);
    }
}