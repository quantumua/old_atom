package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.qe.af.common.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.api.tp.operations.AccountGroupOperations;
import com.betamedia.tp.api.model.AccountGroup;
import com.betamedia.tp.api.model.DealApprovalConfiguration;
import org.apache.commons.lang.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.testng.Assert.assertNotNull;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
@Component
public class AccountGroupOperationsImpl implements AccountGroupOperations {

    private static final Logger logger = LogManager.getLogger(AccountGroupOperationsImpl.class);
    //    for 24option-eu
    private static final String ACCOUNT_GROUP_ID = "662e5241-3862-4b84-9ec2-6828d5ba6a37";
    private AccountGroup accountGroup;

    @Autowired
    private AFTPConnector tpConnector;

    @PostConstruct
    public void init() {
        accountGroup = get(ACCOUNT_GROUP_ID);
    }


    @Override
    public AccountGroup get() {
        return accountGroup;
    }

    @Override
    public AccountGroup get(String id) {
        logger.info("Getting accountGroup for id {}", id);
        AccountGroup accGroup = tpConnector.readById(AccountGroup.class, id);
        assertNotNull(accGroup, "AccountGroup id={} is not available in GS");
        logger.info("Got accountGroup {}", accGroup.toString());
        return accGroup;
    }

    @Override
    public Integer getOpeningDelay(Double amount) {
        return Stream.of(accountGroup.getMarketDealApprovalConfigurations(),
                accountGroup.getDealApprovalConfigurations())
                .flatMap(List::stream)
                .filter(d -> d.getAmount() <= amount)
                .max(Comparator.comparing(DealApprovalConfiguration::getAmount))
                .map(DealApprovalConfiguration::getDelay)
                .orElse(0);
    }

    private AccountGroup create() {
        //TODO implement getting brand -> BrandOperations!
       /* Brand brand = null;
        AccountGroup accountGroup = new AccountGroup();
        accountGroup = new AccountGroup();
        Set<String> brandIds = new HashSet<String>();
        brandIds.add(brand.getValue());
        accountGroup.setBrandIds(brandIds);
        // AG = Auto generated
        accountGroup.setName("AG" + System.currentTimeMillis());
        accountGroup.setDescription("This Account group created by automatic test");
        accountGroup.setPriceType(StrikePriceType.MARKET);
        accountGroup.setExposureLimit(30000);
        accountGroup.setLargePositionThreshold(20000);
        accountGroup.setLargeWinThreshold(15000);
        accountGroup.setConcurrentOrderThreshold(5);

        // Insert
        accountGroup = tpConnector.create(accountGroup);
        return accountGroup;*/
        throw new NotImplementedException();
    }
}
