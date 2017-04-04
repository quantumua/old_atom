package com.betamedia.qe.af.testslibrary.tp;

import com.betamedia.qe.af.core.tests.tp.TPBackEndTest;
import com.betamedia.tp.api.model.Account;
import com.betamedia.tp.api.model.enums.BonusType;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public class BonusTest extends TPBackEndTest {

    /**
     * Add a bonus with a very high sum to an account
     * and verify the bonus was added and the sum is correct
     */
    @Test
    public void addLargeBonus() {
        Account account = operations().accountOperations().getTP();
        double balanceBeforeBonus = account.getBalance();
        double bonusAmount = balanceBeforeBonus * 100;
        double wagerAmount = bonusAmount * 0.1;
        operations().bonusOperations().addBonus(account.getId(), BonusType.REGULAR, bonusAmount, wagerAmount);
        account = operations().accountOperations().getTP(account.getId());
        assertEquals(account.getBalance(), balanceBeforeBonus + bonusAmount);
    }
}
