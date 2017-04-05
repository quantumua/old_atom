package com.betamedia.qe.af.testslibrary.tfoption.end2end;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMAccount;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.dsl.operations.TagOperations;
import com.betamedia.qe.af.core.testingtype.tp.TPEndToEndTest;
import com.betamedia.tp.api.feed.TickData;
import com.betamedia.tp.api.model.Asset;
import com.betamedia.tp.api.model.Option;
import com.betamedia.tp.api.model.Position;
import com.betamedia.tp.api.model.enums.OptionType;
import com.betamedia.tp.api.model.enums.PositionStatus;
import org.testng.Assert;
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
        Asset asset = operations().assetOperations().get();
        Option option = operations().optionOperations().issue(asset.getId(), OptionType.HILO, TagOperations.TagName.NO_CATEGORY);
        TickData tickData = operations().feedOperations().injectFeed(asset.getId(), 5d);
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount binaryAccount = customer.getBinaryAccount();
        operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().topNavigationPage().binary();
        pages().disclaimerNotification().accept();
        pages().bidder()
                .highLow()
                .asset(asset.getId(), asset.getAssetName())
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
