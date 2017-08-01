package com.betamedia.atom.testslibrary.option24.backend.crm;

import com.betamedia.atom.core.testingtype.web.WebBackEndTest;
import com.betamedia.tp.api.model.Account;
import com.betamedia.tp.api.model.enums.BonusType;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public class BonusTest extends WebBackEndTest {

    /**
     * Add a bonus with a very high sum to an account
     * and verify the bonus was added and the sum is correct
     */
    @Test
    public void addLargeBonus() {
        Account account = operations().accountOperations().getCRM();
        double balanceBeforeBonus = account.getBalance();
        double bonusAmount = balanceBeforeBonus * 100;
        double wagerAmount = bonusAmount * 0.1;
        operations().bonusOperations().addBonus(account.getId(), BonusType.REGULAR, bonusAmount, wagerAmount);
        account = operations().accountOperations().getCRM(account.getId());
        assertEquals(account.getBalance(), balanceBeforeBonus + bonusAmount);
    }
}
