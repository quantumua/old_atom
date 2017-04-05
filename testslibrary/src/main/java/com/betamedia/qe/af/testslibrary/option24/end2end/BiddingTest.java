package com.betamedia.qe.af.testslibrary.option24.end2end;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMAccount;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.dsl.operations.TagOperations;
import com.betamedia.qe.af.core.testingtype.tp.TPEndToEndTest;
import com.betamedia.tp.api.model.Asset;
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
        operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.NO_CATEGORY);
        operations().feedOperations().injectFeed(asset.getId(), 1.5d);
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
    }

}
