package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.common.search.criteria.SearchCriteria;
import com.betamedia.qe.af.common.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.api.tp.operations.BrandOperation;
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
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
@Component
public class BrandOperationImpl implements BrandOperation {

    private static final Logger logger = LogManager.getLogger(BrandOperationImpl.class);
    @Value("${af.tp.brandId}")
    private String brandId;
    private Brand brand;

    @Autowired
    private AFTPConnector aftpConnector;

    @PostConstruct
    public void init() {
        brand = get(brandId);
    }

    @Override
    public Brand get() {
        return brand;
    }

    @Override
    public Brand get(String brandId) {
        return aftpConnector.readById(Brand.class, brandId);
    }


    @Override
    public Brand getByName(String brandName) {
        // Replacing '-' in case 24option-eu is under test
        brandName = brandName.replace("-", " ");
        logger.info("Getting  brand '" + brandName + "'");
        SearchCriteria<Brand> criteria = new SearchCriteria<Brand>(Brand.class);
        criteria.and(Brand.EP_NAME.equalsTo(brandName));
        List<Brand> brands = aftpConnector.readMultiple(criteria, null, null).getContent();
        Assert.assertFalse(brands.isEmpty(), "Error!!! The result of getting brand '" + brandName + " is empty");
        Assert.assertTrue(brands.size() == 1, "Error!!! The result of getting brand '" + brandName + " is  List. Brands list size = " + brands.size());
        logger.info("Got brand {}", brands.get(0));
        return brands.get(0);

    }


}
