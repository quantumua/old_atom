package com.betamedia.qe.af.tests.tp;

import com.betamedia.qe.af.core.tests.tp.TPBackEndTest;
import com.betamedia.tp.api.model.Account;
import com.betamedia.tp.api.model.Bonus;
import com.betamedia.tp.api.model.enums.BonusType;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public class BonusTest extends TPBackEndTest {

    @Test
    public void addLargBonus() {
        /*
         * Add a bonus with a very high sum to an account with CRM API --> get
         * account and verify the bonus was added and the sum is correct
         */
        Account account = operations().accountOperations().get();
        double balanceBeforeBonus = account.getBalance();
        double bonusAmount = balanceBeforeBonus * 100;
        double wagerAmount = bonusAmount * 0.1;
        Bonus bonus = operations().bonusOperations().addBonus(account.getId(), BonusType.REGULAR, bonusAmount, wagerAmount);
        account = operations().accountOperations().get(account.getId());
        assertEquals(account.getBalance(), balanceBeforeBonus + bonusAmount);
    }
}
