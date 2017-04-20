package com.betamedia.qe.af.testslibrary.option24.web.bmw.smoke;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMAccount;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.dsl.operations.TagOperations;
import com.betamedia.qe.af.core.testingtype.tp.TPEndToEndTest;
import com.betamedia.tp.api.model.Asset;
import com.betamedia.tp.api.model.Position;
import com.betamedia.tp.api.model.enums.OptionType;
import com.betamedia.tp.api.model.enums.PositionStatus;

/**
 * @author leonid.a
 *
 */
public class ShortTermPositionsOpeningClosing extends TPEndToEndTest {

	@Test	
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
        
        String minInvestment = "15.0"; 	//TODO GetMinInvestment for Game and Category
        pages().bidder()
                .bidLow()
                .setAmount(minInvestment)
                .confirm();
        
        List<String> positionIds = pages().positions().get();
        Position position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size() - 1));
        assertEquals(position.getStatus(), PositionStatus.OPEN);
	}
	
	@Test
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
        pages().disclaimerNotification().accept();
        
        int investedAmount = 15; 		//TODO it must equal to this function from legacy framework: AssetTestingUtils.getMinInvestment(game, category);
        Double maxInvestment = 150.0; 	//TODO it must equal to this function from legacy framework: GetMaxInvestment for Game and Category
        
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
}
