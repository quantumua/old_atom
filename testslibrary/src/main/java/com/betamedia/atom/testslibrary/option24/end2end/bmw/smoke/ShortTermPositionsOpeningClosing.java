package com.betamedia.atom.testslibrary.option24.end2end.bmw.smoke;

import com.betamedia.atom.core.api.tp.entities.response.CRMAccount;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.dsl.operations.TagOperations;
import com.betamedia.atom.core.testingtype.tp.TPEndToEndTest;
import com.betamedia.tp.api.feed.TickData;
import com.betamedia.tp.api.model.AccountGroup;
import com.betamedia.tp.api.model.Asset;
import com.betamedia.tp.api.model.Position;
import com.betamedia.tp.api.model.enums.OptionType;
import com.betamedia.tp.api.model.enums.PositionStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * @author leonid.a
 */
public class ShortTermPositionsOpeningClosing extends TPEndToEndTest {

	/*
	 * [TestLink] TP-4330:Open a position that expires in 60 seconds with invested amount = minimum allowed
     * In this test we will check that when we are opening a Short term (60 sec)  position with invested
     * amount that is equals to the minimum allowed, a position is opened and the  functionality is valid.
     */
	@Test()	
    public void openAPositionThatExpiresIn60SecondsWithInvestedAmountMinimumAllowed() {
        Asset asset = operations().assetOperations().get();
        operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
        operations().feedOperations().injectFeed(asset.getId(), 1.5d);
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount binaryAccount = customer.getBinaryAccount();
        operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
        pages().topNavigationPage().logIn();
        //pages().loginPage().login(customer.getUserName(), CustomerBuilder.DEFAULT_PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
        
        String minInvestment = "15.0"; 	//TODO GetMinInvestment for Game and Category, see https://devredmine/issues/65353
        pages().binaryBidder()
                .bidLow()
                .setAmount(minInvestment)
                .confirm();

        String lastPosId = pages().binaryPositions().getLatest();
        Position position = operations().positionOperations().getByDisplayId(lastPosId);
        assertEquals(position.getStatus(), PositionStatus.OPEN);
    }

	/*
	 * [TestLink] TP-4326:Open a position that expires in 60 seconds with invested amount = maximum allowed
     * In this test we will check that when we are opening a Short term (60 sec)  position with invested
     * amount that is equals to the maximum allowed, a position is opened and the  functionality is valid.
     */
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
        //pages().loginPage().login(customer.getUserName(), CustomerBuilder.DEFAULT_PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().topNavigationPage().binary();
        pages().binarySelector().highLow();
        pages().disclaimerNotification().tryAccept();

        // see https://devredmine/issues/65353
        int investedAmount = 15;        //TODO it must equal to this function from legacy framework: AssetTestingUtils.getMinInvestment(game, category);
        Double maxInvestment = 150.0;   //     it must equal to this function from legacy framework: GetMaxInvestment for Game and Category

//		Legacy framework calls:
//        String baseCurrenacyPositionDisplayId = pomHolder.bidder().openPosition(webDriver, asset.getAssetName(), testCaseOption, BinarySelection.LOW, String.valueOf(investedAmount), game, category, true);
//        Position position = PositionTestingManager.getInstance().getPosition(baseCurrenacyPositionDisplayId);
//?????????????
        List<String> positionIds = pages().binaryPositions().get();
        Position position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size()));
//?????????????
        //PositionTestingManager.getInstance().cancelPosition(position, "Test '" + testCaseDisplayId + "'");
        double baseCurrencyMinAmout = position.getBaseCurrencyAmount();
        double conversionRate = baseCurrencyMinAmout / investedAmount;
        int baseCurrencyMaxInvestmentLimit = (int) (maxInvestment * conversionRate * 2);
        //		TODO: This can be done after method completenes: com.betamedia.atom.core.dsl.operations.impl.AccountGroupOperationsImpl.Create()
        // Redmine task: https://devredmine/issues/65354
    if (operations().accountGroupOperations().get().getExposureLimit() <= baseCurrencyMaxInvestmentLimit) {     
        //if (accountGroup.getExposureLimit() <= baseCurrencyMaxInvestmentLimit) {
    	operations().accountGroupOperations().get().setExposureLimit(baseCurrencyMaxInvestmentLimit);
            Set<String> propertiesSet = new HashSet<String>();
            propertiesSet.add(AccountGroup.EP_EXPOSURE_LIMIT.getName());
            //AccountGroupTestingManager.getInstance().updateEntity(accountGroup, propertiesSet);
        }
  
        Double accountBalance = operations().accountOperations().getTP(accountId).getBalance();
        if (accountBalance <= maxInvestment) {
            operations().accountOperations().updateBalanceTP(accountId, (double) maxInvestment + 200);
        }

        pages().binaryBidder()
                .setAmount(maxInvestment.toString())
                .bidLow()
                .confirm();

//        List<String> positionIds = pages().binaryPositions().get();
        position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size() - 1));
        assertEquals(position.getStatus(), PositionStatus.OPEN);
    }

	/*
	 * [TestLink] TP-3759:Open a position that expires in 60 seconds - Basic Functionality
    * In this test we will check that when we are opening a Short Term position, the basic functionality is valid.
    */
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
        //pages().loginPage().login(customer.getUserName(), CustomerBuilder.DEFAULT_PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
        // see https://devredmine/issues/65353
        String minInvestment = "15.0"; 	//TODO it must equal to this function from legacy framework: AssetTestingUtils.getMinInvestment(game, category);
        pages().binaryBidder()
                .bidLow()
                .setAmount(minInvestment)
                .confirm();

        List<String> positionIds = pages().binaryPositions().get();
        Position position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size() - 1));
        assertEquals(position.getStatus(), PositionStatus.OPEN);
    }
	/*
	 * [TestLink] TP-3758:Open a position that expires in 2 minutes - Basic Functionality
    * In this test we will check that when we are opening a Short Term position, the basic functionality is valid.
    */
	@Test 
    public void OpenAPositionThatEexpiresIn2MinutesBasicFunctionality() {
        Asset asset = operations().assetOperations().get();
        operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_2_MIN_GAME_H3_TEXT);
        operations().feedOperations().injectFeed(asset.getId(), 1.5d);
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount binaryAccount = customer.getBinaryAccount();
        operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
        pages().topNavigationPage().logIn();
       // pages().loginPage().login(customer.getUserName(), CustomerBuilder.DEFAULT_PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());

        // see https://devredmine/issues/65353
        String minInvestment = "15.0"; 	//TODO it must equal to this function from legacy framework: AssetTestingUtils.getMinInvestment(game, category);
        pages().binaryBidder()
                .bidLow()
                .setAmount(minInvestment)
                .confirm();

        List<String> positionIds = pages().binaryPositions().get();
        Position position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size() - 1));
        assertEquals(position.getStatus(), PositionStatus.OPEN);
    }
	/*
	 * [TestLink] TP-3757:Open a position that expires in 5 minutes - Basic Functionality
    * In this test we will check that when we are opening a Short Term position, the basic functionality is valid.
    */
	@Test 
	public void OpenAPositionThatEexpiresIn5MinutesBasicFunctionality(){
		Asset asset = operations().assetOperations().get();
        operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_5_MIN_GAME_H3_TEXT);
        operations().feedOperations().injectFeed(asset.getId(), 1.5d);
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount binaryAccount = customer.getBinaryAccount();
        operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
        pages().topNavigationPage().logIn();
        //pages().loginPage().login(customer.getUserName(), CustomerBuilder.DEFAULT_PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());

        // see https://devredmine/issues/65353
        String minInvestment = "15.0"; 	//TODO it must equal to this function from legacy framework: AssetTestingUtils.getMinInvestment(game, category);
        pages().binaryBidder()
                .bidLow()
                .setAmount(minInvestment)
                .confirm();

        List<String> positionIds = pages().binaryPositions().get();
        Position position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size() - 1));
        assertEquals(position.getStatus(), PositionStatus.OPEN);
	}
	
	/*
	 * [TestLink] TP-3686:Position Indication - check that the color of the cursor changes successfully on win and lose
     * Position Indication - check that the color of the cursor changes
     * successfully on win and lose Winning - Green. Loosing - Red.
     */
    @Test(description = "TP-3686:Position Indication - check that the color of the cursor changes successfully on win and lose")
    public void PositionIndicationCheckThatTheColorOfTheCursorChangesSuccessfullyOnWinAndLose() {
        Asset asset = operations().assetOperations().get();
        operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
        TickData tickData = operations().feedOperations().injectFeed(asset.getId(), 1.5d);
        CRMCustomer customer = operations().customerOperations().register();
        CRMAccount binaryAccount = customer.getBinaryAccount();
        operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
        pages().topNavigationPage().logIn();
        //pages().loginPage().login(customer.getUserName(), CustomerBuilder.DEFAULT_PASSWORD);
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());

        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
        
        // see https://devredmine/issues/65353
        String minInvestment = "15.0"; 	//TODO GetMinInvestment for Game and Category
        pages().binaryBidder()
                .bidLow()
                .setAmount(minInvestment)
                .confirm();

        String lowPositionDisplayId = pages().binaryPositions().getLatest();
        Position lowPosition = operations().positionOperations().getByDisplayId(lowPositionDisplayId);

        pages().binaryBidder()
                .bidHigh()
                .setAmount(minInvestment)
                .confirm();

        String highPositionDisplayId = pages().binaryPositions().getLatest();
        Position highPosition = operations().positionOperations().getByDisplayId(highPositionDisplayId);
      
      // Task for LeonidA: https://devredmine/issues/65355
      // Task for VadimS: https://devredmine/issues/65324
//the logic of forming lower and hire then position value should be inside appropriate operations
        double spread = lowPosition.getSpread() != null ? lowPosition.getSpread() : 0;
//        TODO: why did you break the original logic? UISyncTesting.tradeRowDisplayAfterWinOrLose: 1455
//        you'll get a wrong result
/*        we need two methods in feed operations
 feedOperations().injectFeedHigher(assetId, position)
 feedOperations().injectFeedLower(....)
 the logic should be incapsulated inside
* */
      operations().feedOperations().injectFeed(asset.getId(), tickData.getSpot() - spread - 0.5d);
      
        operations().positionOperations().getExpired(lowPosition);
        operations().positionOperations().getExpired(highPosition);

//        TODO logic and assertions should be inside appropriate methods checkWin(...) and checkLose(....), also you are breaking the incapsulation of webElements inside pageObjects
        WebElement lowPosRow = pages().binaryPositions().getTradeRow(lowPositionDisplayId);
        WebElement highPosRow = pages().binaryPositions().getTradeRow(highPositionDisplayId);

        Assert.assertEquals(lowPosRow.getAttribute("class").contains("bmPositive"), true);
        Assert.assertEquals(lowPosRow.getAttribute("class").contains("bmWin"), true);
        Assert.assertEquals(highPosRow.getAttribute("class").contains("bmNegative"), true);
        Assert.assertEquals(highPosRow.getAttribute("class").contains("bmLose"), true);

    }

}
