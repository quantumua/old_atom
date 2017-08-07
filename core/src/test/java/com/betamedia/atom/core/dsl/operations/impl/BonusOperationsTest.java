package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.api.tp.adapters.TPCRMHttpAdapter;
import com.betamedia.atom.core.api.tp.entities.response.CRMAddBonus;
import com.betamedia.atom.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.tp.api.model.Bonus;
import com.betamedia.tp.api.model.Brand;
import com.betamedia.tp.api.model.enums.BonusType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doAnswer;

/**
 * Created by Oleksandr Losiev on 4/19/17.
 */
public class BonusOperationsTest {
    private static class QAEnvBonusOperationsImpl extends AbstractBonusOperations<QAEnvironment> {
        @Override
        public EnvironmentType getEnvironment() {
            return EnvironmentType.QA;
        }
    }

    @InjectMocks
    private QAEnvBonusOperationsImpl bonusOperations;

    @Mock
    private TPCRMHttpAdapter crmHttpAdapter;

    private String bonusId = "testBonusId";
    private String bonusDisplayId = "testBonusDisplayId";

    private String brandId = "testBrandId";
    private String brandDisplayId = "testBrandDisplayId";

    private String accountId = "testAccountId";

    private double amount = 225;
    private double wagerAmount = 345;
    private BonusType bonusType = BonusType.REGULAR;

    private Brand brand;

    @BeforeClass
    public void setupClass() throws Exception {
        brand = new Brand();
        brand.setId(brandId);
        brand.setDisplayId(brandDisplayId);
    }

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    //TODO fix test after AccountOperations have been migrated from GigaSpaces properly
    @Test(enabled = false)
    public void testAddBonus() {
        doAnswer(invocationOnMock -> {
            CRMAddBonus crmAddBonus = new CRMAddBonus(bonusDisplayId);
            return new TPCRMResponse<>(crmAddBonus, Collections.emptyList());
        }).when(crmHttpAdapter).addBonus(accountId, bonusType, amount, wagerAmount, brandDisplayId);

        Bonus actualBonus = bonusOperations.addBonus(accountId, bonusType, amount, wagerAmount);
        assertThat(getExpectedBonus(), new ReflectionEquals(actualBonus));
    }

    private Bonus getExpectedBonus() {
        Bonus bonus = new Bonus();
        bonus.setId(bonusId);
        bonus.setDisplayId(bonusDisplayId);
        bonus.setAmount(amount);
        bonus.setWagerAmount(wagerAmount);
        bonus.setBonusType(bonusType);
        return bonus;
    }
}
