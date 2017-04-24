package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.common.search.criteria.SearchCriteria;
import com.betamedia.qe.af.core.connectors.tp.FWTPConnector;
import com.betamedia.qe.af.core.dsl.operations.BrandOperations;
import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.environment.tp.properties.EntityPropertiesHolder;
import com.betamedia.tp.api.model.Brand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * This class is designed to facilitate the execution of common operations related to brand.
 * It can be used as a "building block" when writing integration tests.
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 * @see Brand
 */
public abstract class AbstractBrandOperations<T extends EnvironmentDependent> implements BrandOperations<T> {

    private static final Logger logger = LogManager.getLogger(AbstractBrandOperations.class);

    private Brand brand;

    @Autowired
    private FWTPConnector<T> tpConnector;

    @Autowired
    private EntityPropertiesHolder<T> entityPropertiesHolder;

    @PostConstruct
    public void init() {
        brand = get(entityPropertiesHolder.getBrandId());
    }

    /**
     * A method to get a brand by default id.
     */
    @Override
    public Brand get() {
        return brand;
    }

    /**
     * A method to get brand by given id.
     */
    @Override
    public Brand get(String brandId) {
        return tpConnector.readById(Brand.class, brandId);
    }

    /**
     * A method to get brand by name.
     * It is assumed that there is one and only one brand with the specified name.
     */
    @Override
    public Brand getByName(String brandName) {
        // Replacing '-' in case 24option-eu is under test
        brandName = brandName.replace("-", " ");
        logger.info("Getting  brand '" + brandName + "', {}", getEnvironment());
        SearchCriteria<Brand> criteria = new SearchCriteria<Brand>(Brand.class);
        criteria.and(Brand.EP_NAME.equalsTo(brandName));
        List<Brand> brands = tpConnector.readMultiple(criteria, null, null).getContent();
        Assert.assertFalse(brands.isEmpty(), "Error!!! The result of getting brand '" + brandName + " is empty, {}");
        Assert.assertTrue(brands.size() == 1, "Error!!! The result of getting brand '" + brandName + " is  List. Brands list size = " + brands.size());
        logger.info("Got brand {}, {}", brands.get(0), getEnvironment());
        return brands.get(0);

    }


}
