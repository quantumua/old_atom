package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.qe.af.common.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.api.tp.operations.AccountGroupOperations;
import com.betamedia.tp.api.model.AccountGroup;
import org.apache.commons.lang.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
@Component
public class AccountGroupOperationsImpl implements AccountGroupOperations {

    private static final Logger logger = LogManager.getLogger(AccountOperationsImpl.class);
    //    for 24option-eu
    private static final String ACCOUNT_GROUP_ID = "662e5241-3862-4b84-9ec2-6828d5ba6a37";
    private AccountGroup accountGroup;

    @Autowired
    private AFTPConnector tpConnector;

    @PostConstruct
    public void init(){
        accountGroup = get(ACCOUNT_GROUP_ID);
    }


    @Override
    public AccountGroup get() {
        return accountGroup;
    }

    @Override
    public AccountGroup get(String id) {
        AccountGroup accountGroup = null;
        try {
            logger.info("Getting accountGroup for id {}", id);
            accountGroup = tpConnector.readById(AccountGroup.class, id);
            logger.info("Got accountGroup {}", accountGroup.toString());
        } catch (Throwable e) {
            logger.error("", e);
        }
        return accountGroup;
    }

    @Override
    public AccountGroup getOrCreate(String id) {
        //TODO Implement!
        throw new NotImplementedException();
    }

    private AccountGroup create() {
        //TODO implement getting brand -> BrandOperation!
       /* Brand brand = null;
        AccountGroup accountGroup = new AccountGroup();
        accountGroup = new AccountGroup();
        Set<String> brandIds = new HashSet<String>();
        brandIds.add(brand.getId());
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
