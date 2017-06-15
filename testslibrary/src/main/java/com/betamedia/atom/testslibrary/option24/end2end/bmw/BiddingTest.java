package com.betamedia.atom.testslibrary.option24.end2end.bmw;

import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMAccount;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.dsl.operations.TagOperations;
import com.betamedia.atom.core.testingtype.tp.TPEndToEndTest;
import com.betamedia.tp.api.model.Asset;
import com.betamedia.tp.api.model.Position;
import com.betamedia.tp.api.model.enums.OptionType;
import com.betamedia.tp.api.model.enums.PositionStatus;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by mbelyaev on 3/23/17.
 */
public class BiddingTest extends TPEndToEndTest {

    @Test
    public void biddingTest() {
        Asset asset = operations().assetOperations().get();
        operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.NO_CATEGORY);
        operations().feedOperations().injectFeed(asset.getId(), 1.5d);
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount binaryAccount = customer.getBinaryAccount();
        operations().accountOperations().depositCRM(binaryAccount.getId(), 1000d);
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
        pages().binaryBidder()
                .setAmount("10.0")
                .bidHigh()
                .confirm();
        String openedPositionId = pages().binaryPositions().getLatest();
        Position position = operations().positionOperations().getByDisplayId(openedPositionId);
        Assert.assertEquals(position.getStatus(), PositionStatus.OPEN);
    }

}
