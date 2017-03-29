package com.betamedia.qe.af.tests.tp;

import com.betamedia.qe.af.core.tests.tp.TPEndToEndTest;
import com.betamedia.tp.api.model.Position;
import com.betamedia.tp.api.model.enums.OrderStatus;
import com.betamedia.tp.api.model.enums.PositionStatus;
import com.betamedia.tp.api.model.order.Order;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by mbelyaev on 3/23/17.
 */
public class BiddingTest extends TPEndToEndTest {

    //WIP
    @Test
    public void biddingTest() {
//        Asset asset = operations().assetOperations().get();
//        Option option = operations().optionOperations().issue(asset.getValue(), OptionType.HILO, TagOperations.TagName.NO_CATEGORY);
//        TickData tickData = operations().feedOperations().injectFeed(asset.getValue(), 5d);
        pages().topNavigationPage().logIn();
        pages().loginPage().login("vasichka", "123123");
        pages().disclaimerNotification().accept();
        pages().bidder()
                .highLow()
                .asset()
                .setAmount("10.0")
                .bidHigh()
                .confirm();
        List<String> positionIds = pages().positions().get();
        Position position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size() - 1));
        assertEquals(position.getStatus(), PositionStatus.OPEN);
//        operations().feedOperations().injectFeed(order.getAssetId(), tickData.getSpot() + 0);
//        order = operations().orderOperations().waitForStatusChange(order);
//        assertEquals(order.getOrderStatus(), OrderStatus.EXECUTED);
//        Position position = operations().positionOperations().get(order.getPosition().getValue());
//        assertEquals(position.getStatus(), PositionStatus.OPEN);
//        assertEquals(position.getBinarySelection(), BinarySelection.HIGH);

    }

}
