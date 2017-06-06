package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.dsl.operations.AccountGroupOperations;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.environment.tp.properties.EntityPropertiesHolder;
import com.betamedia.tp.api.model.AccountGroup;
import com.betamedia.tp.api.model.DealApprovalConfiguration;
import org.apache.commons.lang.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.testng.Assert.assertNotNull;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/21/17.
 */
public abstract class AbstractAccountGroupOperations<T extends EnvironmentDependent> implements AccountGroupOperations<T> {

    private static final Logger logger = LogManager.getLogger(AbstractAccountGroupOperations.class);

    @Autowired
    private EntityPropertiesHolder<T> entityPropertiesHolder;

    @Autowired
    private FWTPConnector<T> tpConnector;

    private AccountGroup accountGroup;

    @PostConstruct
    public void init() {
        accountGroup = get(entityPropertiesHolder.getDefaultAccountGroupId());
    }

    /**
     * This method is used to get account group by default id.
     */
    @Override
    public AccountGroup get() {
        return accountGroup;
    }

    /**
     * This method is used to get account group by id.
     */
    @Override
    public AccountGroup get(String id) {
        logger.info("Getting accountGroup for id {}, {} env", id, getEnvironment());
        AccountGroup accGroup = tpConnector.readById(AccountGroup.class, id);
        assertNotNull(accGroup, "AccountGroup id={} is not available in GS, " + getEnvironment() + " env");
        logger.info("Got accountGroup {}, {} env", accGroup.toString(), getEnvironment());
        return accGroup;
    }

    /**
     * This method is used to calculate the opening delay based on deal amount and configurations.
     * A deal, which has the highest amount below threshold, is selected among group's market and deal approval configurations.
     * Then its opening delay is returned.
     * @param amount threshold amount
     * @return opening delay
     */
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
