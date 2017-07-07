package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.configuration.properties.EntityProperties;
import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.common.search.Page;
import com.betamedia.tp.api.model.Brand;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * Created by Oleksandr Losiev on 4/19/17.
 */
public class BrandOperationsTest {
    private static class QAEnvBrandOperationsImpl extends AbstractBrandOperations<QAEnvironment> {
        @Override
        public EnvironmentType getEnvironment() {
            return EnvironmentType.QA;
        }
    }

    @InjectMocks
    private QAEnvBrandOperationsImpl brandOperations;

    @Mock
    private FWTPConnector tpConnector;

    @Mock
    private Page<Brand> brandPage;

    @Mock
    private EntityProperties<QAEnvironment> entityProperties;

    private String brandId = "testBrandId";
    private String brandName = "testBrandName";
    private String brandDescription = "testBrandDescription";

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(entityProperties.getBrandId()).thenReturn(brandId);
        when(tpConnector.readById(Brand.class, brandId)).thenReturn(getExpectedBrand());
        doReturn(brandPage).when(tpConnector).readMultiple(any(), any(), any());

        brandOperations.init();
    }

    @Test
    public void testGetBrand() {
        Brand actualBrand = brandOperations.get();
        assertThat(getExpectedBrand(), new ReflectionEquals(actualBrand));
    }

    @Test
    public void testGetUnavailableBrand() {
        Brand actualBrand = brandOperations.get("unavailable");
        assertNull(actualBrand);
    }

    @Test
    public void testGetBrandById() {
        Brand actualBrand = brandOperations.get(brandId);
        assertThat(getExpectedBrand(), new ReflectionEquals(actualBrand));
    }

    @Test
    public void testGetBrandByName() {
        List<Brand> brands = new ArrayList<>();
        brands.add(getExpectedBrand());
        when(brandPage.getContent()).thenReturn(brands);

        Brand actualBrand = brandOperations.getByName(brandName);
        assertThat(getExpectedBrand(), new ReflectionEquals(actualBrand));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testGetMultipleBrandsByName() {
        List<Brand> brands = new ArrayList<>();
        brands.add(getExpectedBrand());
        brands.add(getExpectedBrand());
        when(brandPage.getContent()).thenReturn(brands);

        brandOperations.getByName(brandName);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testNoBrandsByName() {
        when(brandPage.getContent()).thenReturn(Collections.emptyList());
        brandOperations.getByName(brandName);
    }

    private Brand getExpectedBrand() {
        Brand brand = new Brand();
        brand.setId(brandId);
        brand.setName(brandName);
        brand.setDescription(brandDescription);
        return brand;
    }
}
