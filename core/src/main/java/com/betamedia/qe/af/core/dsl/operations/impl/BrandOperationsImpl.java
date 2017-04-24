package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.common.search.criteria.SearchCriteria;
import com.betamedia.qe.af.core.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.dsl.operations.BrandOperations;
import com.betamedia.tp.api.model.Brand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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
@Component
public class BrandOperationsImpl implements BrandOperations {

    private static final Logger logger = LogManager.getLogger(BrandOperationsImpl.class);
    @Value("${af.tp.brandId}")
    private String brandId;
    private Brand brand;

    @Autowired
    private AFTPConnector tpConnector;

    @PostConstruct
    public void init() {
        brand = get(brandId);
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
        logger.info("Getting  brand '" + brandName + "'");
        SearchCriteria<Brand> criteria = new SearchCriteria<Brand>(Brand.class);
        criteria.and(Brand.EP_NAME.equalsTo(brandName));
        List<Brand> brands = tpConnector.readMultiple(criteria, null, null).getContent();
        Assert.assertFalse(brands.isEmpty(), "Error!!! The result of getting brand '" + brandName + " is empty");
        Assert.assertTrue(brands.size() == 1, "Error!!! The result of getting brand '" + brandName + " is  List. Brands list size = " + brands.size());
        logger.info("Got brand {}", brands.get(0));
        return brands.get(0);

    }


}
