package com.betamedia.qe.af.testslibrary.option24.web.bmw.smoke;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMAccount;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.dsl.operations.TagOperations;
import com.betamedia.qe.af.core.testingtype.tp.TPEndToEndTest;
import com.betamedia.tp.api.model.Asset;
import com.betamedia.tp.api.model.Position;
import com.betamedia.tp.api.model.enums.OptionType;
import com.betamedia.tp.api.model.enums.PositionStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author leonid.a
 */
public class ShortTermPositionsOpeningClosing extends TPEndToEndTest {

    @Test()
    public void openAPositionThatExpiresIn60SecondsWithInvestedAmountMinimumAllowed() {
        Asset asset = operations().assetOperations().get();
        operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
        operations().feedOperations().injectFeed(asset.getId(), 1.5d);
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount binaryAccount = customer.getBinaryAccount();
        operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().accept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());

        String minInvestment = "15.0";    //TODO GetMinInvestment for Game and Category
        pages().bidder()
                .bidLow()
                .setAmount(minInvestment)
                .confirm();

        List<String> positionIds = pages().positions().get();
        Position position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size() - 1));
        assertEquals(position.getStatus(), PositionStatus.OPEN);
    }

    @Test()
    public void openAPositionThatExpiresIn60SecondsWithInvestedAmountMaximumAllowed() {
        Asset asset = operations().assetOperations().get();
        operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
        operations().feedOperations().injectFeed(asset.getId(), 1.5d);
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount binaryAccount = customer.getBinaryAccount();
        String accountId = binaryAccount.getId();
        operations().accountOperations().depositCRM(accountId, 100d);
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().topNavigationPage().binary();
        pages().binarySelector().highLow();
        pages().disclaimerNotification().accept();

        int investedAmount = 15;        //TODO it must equal to this function from legacy framework: AssetTestingUtils.getMinInvestment(game, category);
        Double maxInvestment = 150.0;    //TODO it must equal to this function from legacy framework: GetMaxInvestment for Game and Category

//		Legacy framework calls:
//        String baseCurrenacyPositionDisplayId = pomHolder.bidder().openPosition(webDriver, asset.getAssetName(), testCaseOption, BinarySelection.LOW, String.valueOf(investedAmount), game, category, true);
//        Position position = PositionTestingManager.getInstance().getPosition(baseCurrenacyPositionDisplayId);
//?????????????
        List<String> positionIds = pages().positions().get();
        Position position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size()));
//?????????????
        //PositionTestingManager.getInstance().cancelPosition(position, "Test '" + testCaseDisplayId + "'");

        double baseCurrencyMinAmout = position.getBaseCurrencyAmount();
        double conversionRate = baseCurrencyMinAmout / investedAmount;
        int baseCurrencyMaxInvestmentLimit = (int) (maxInvestment * conversionRate * 2);

//		TODO: This can be done after method completenes: com.betamedia.qe.af.core.dsl.operations.impl.AccountGroupOperationsImpl.Create()  
/*    if (operations().accountGroupOperations().getExposureLimit() <= baseCurrencyMaxInvestmentLimit) {     
        //if (accountGroup.getExposureLimit() <= baseCurrencyMaxInvestmentLimit) {
        	accountGroup.setExposureLimit(baseCurrencyMaxInvestmentLimit);
            Set<String> propertiesSet = new HashSet<String>();
            propertiesSet.add(AccountGroup.EP_EXPOSURE_LIMIT.getName());
            AccountGroupTestingManager.getInstance().updateEntity(accountGroup, propertiesSet);
        }
  */
        Double accountBalance = operations().accountOperations().getTP(accountId).getBalance();
        if (accountBalance <= maxInvestment) {
            operations().accountOperations().updateBalanceTP(accountId, (double) maxInvestment + 200);
        }

        pages().bidder()
                .setAmount(maxInvestment.toString())
                .bidLow()
                .confirm();

//        List<String> positionIds = pages().positions().get();
        position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size() - 1));
        assertEquals(position.getStatus(), PositionStatus.OPEN);
    }

    @Test
//TODO    fully duplicates the code of the first test
    public void OpenAPositionThatExpiresIn60SecondsBasicFunctionality() {
        Asset asset = operations().assetOperations().get();
        operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
        operations().feedOperations().injectFeed(asset.getId(), 1.5d);
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount binaryAccount = customer.getBinaryAccount();
        operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().accept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());

        String minInvestment = "15.0";    //TODO it must equal to this function from legacy framework: AssetTestingUtils.getMinInvestment(game, category);
        pages().bidder()
                .bidLow()
                .setAmount(minInvestment)
                .confirm();

        List<String> positionIds = pages().positions().get();
        Position position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size() - 1));
        assertEquals(position.getStatus(), PositionStatus.OPEN);
    }

    @Test
    public void OpenAPositionThatEexpiresIn2MinutesBasicFunctionality() {
        Asset asset = operations().assetOperations().get();
        operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_2_MIN_GAME_H3_TEXT);
        operations().feedOperations().injectFeed(asset.getId(), 1.5d);
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount binaryAccount = customer.getBinaryAccount();
        operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().accept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());

        String minInvestment = "15.0";    //TODO it must equal to this function from legacy framework: AssetTestingUtils.getMinInvestment(game, category);
        pages().bidder()
                .bidLow()
                .setAmount(minInvestment)
                .confirm();

        List<String> positionIds = pages().positions().get();
        Position position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size() - 1));
        assertEquals(position.getStatus(), PositionStatus.OPEN);
    }

    @Test
    public void OpenAPositionThatEexpiresIn5MinutesBasicFunctionality() {
        Asset asset = operations().assetOperations().get();
        operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_5_MIN_GAME_H3_TEXT);
        operations().feedOperations().injectFeed(asset.getId(), 1.5d);
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount binaryAccount = customer.getBinaryAccount();
        operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().accept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());

        String minInvestment = "15.0";    //TODO it must equal to this function from legacy framework: AssetTestingUtils.getMinInvestment(game, category);
        pages().bidder()
                .bidLow()
                .setAmount(minInvestment)
                .confirm();

        List<String> positionIds = pages().positions().get();
        Position position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size() - 1));
        assertEquals(position.getStatus(), PositionStatus.OPEN);
    }

    @Test(description = "Check that the color of the cursor changes successfully on win and lose")
    public void PositionIndicationCheckThatTheColorOfTheCursorChangesSuccessfullyOnWinAndLose() {
        Asset asset = operations().assetOperations().get();
        operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
        operations().feedOperations().injectFeed(asset.getId(), 1.5d);
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount binaryAccount = customer.getBinaryAccount();
        operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());

        pages().disclaimerNotification().accept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
        String minInvestment = "15.0";    //TODO GetMinInvestment for Game and Category
        pages().bidder()
                .bidLow()
                .setAmount(minInvestment)
                .confirm();

        String lowPositionDisplayId = pages().positions().getLast();
        Position lowPosition = operations().positionOperations().getByDisplayId(lowPositionDisplayId);

        pages().bidder()
                .bidHigh()
                .setAmount(minInvestment)
                .confirm();

        String highPositionDisplayId = pages().positions().getLast();
        Position highPosition = operations().positionOperations().getByDisplayId(highPositionDisplayId);
//the logic of forming lower and hire then position value should be inside appropriate operations
        double spread = lowPosition.getSpread() != null ? lowPosition.getSpread() : 0;
//        TODO: why did you brake the original logic? UISyncTesting.tradeRowDisplayAfterWinOrLose: 1455
//        you'll get a wrong result
/*        we need two methods in feed operations
 feedOperations().injectFeedHire(assetId, position)
 feedOperations().injectFeedLower(....)
 the logic should be incapsulated inside
* */
        operations().feedOperations().injectFeed(asset.getId(), spread - 0.5d);

        operations().positionOperations().getExpired(lowPosition);
        operations().positionOperations().getExpired(highPosition);

//        TODO logic and assertions should be inside approppriate methods checkWin(...) and checkLose(....), also you are breaking the incapsulation of webElements inside pageObjects
        WebElement lowPosRow = pages().positions().getTradeRow(lowPositionDisplayId);
        WebElement highPosRow = pages().positions().getTradeRow(highPositionDisplayId);

        Assert.assertEquals(lowPosRow.getAttribute("class").contains("bmPositive"), true);
        Assert.assertEquals(lowPosRow.getAttribute("class").contains("bmWin"), true);
        Assert.assertEquals(highPosRow.getAttribute("class").contains("bmNegative"), true);
        Assert.assertEquals(highPosRow.getAttribute("class").contains("bmLose"), true);

    }
}
