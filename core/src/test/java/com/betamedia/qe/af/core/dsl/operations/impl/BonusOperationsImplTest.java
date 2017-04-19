package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.api.tp.adapters.TPCRMHttpAdapter;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMAddBonus;
import com.betamedia.qe.af.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.qe.af.core.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.dsl.operations.BrandOperations;
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
import static org.mockito.Mockito.when;

/**
 * Created by Oleksandr Losiev on 4/19/17.
 */
public class BonusOperationsImplTest {

    @InjectMocks
    private BonusOperationsImpl bonusOperations;

    @Mock
    private AFTPConnector tpConnector;

    @Mock
    private TPCRMHttpAdapter crmHttpAdapter;

    @Mock
    private BrandOperations brandOperations;

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
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(tpConnector.readById(Bonus.class, bonusId)).thenReturn(getExpectedBonus());
        when(tpConnector.readByDisplayId(Bonus.class, bonusDisplayId)).thenReturn(getExpectedBonus());

        brand = new Brand();
        brand.setId(brandId);
        brand.setDisplayId(brandDisplayId);
    }

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);

        when(tpConnector.readById(Bonus.class, bonusId)).thenReturn(getExpectedBonus());
        when(tpConnector.readByDisplayId(Bonus.class, bonusDisplayId)).thenReturn(getExpectedBonus());
        when(brandOperations.get()).thenReturn(brand);
    }

    @Test
    public void testGetBonus() {
        Bonus actualBonus = bonusOperations.get(bonusId);
        assertThat(getExpectedBonus(), new ReflectionEquals(actualBonus));
    }

    @Test
    public void testGetBonusByDisplayId() {
        Bonus actualBonus = bonusOperations.getByDisplayId(bonusDisplayId);
        assertThat(getExpectedBonus(), new ReflectionEquals(actualBonus));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testGetUnavailableBonus() {
        bonusOperations.get("unavailable");
    }

    @Test
    public void testAddBonus() {
        doAnswer(invocationOnMock -> {
            CRMAddBonus crmAddBonus = new CRMAddBonus(bonusDisplayId);
            return new TPCRMResponse<CRMAddBonus>(crmAddBonus, Collections.emptyList());
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
