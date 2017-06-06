package com.betamedia.atom.core.fwdataaccess.repository.impl;

import com.betamedia.atom.core.fwdataaccess.repository.util.RepositoryVersion;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;

/**
 * @author mbelyaev
 * @since 6/1/17
 */
@RunWith(MockitoJUnitRunner.class)
public class VersionedWebElementRepositoryTest {
    private static final WebElementRepository webElementRepository = new WebElementRepository();
    private static final String testRepositoryPath = "/webobjects/testWebObjectRepository.csv";
    private static final String pageObjectName = "TestObject1";
    private static final String locatorId = "testLocator1";

    @BeforeClass
    public static void populateRepository() throws Exception {
        webElementRepository.setWebObjectsPath(testRepositoryPath);
        webElementRepository.populateRepository();
    }

    /**
     * Exact matches
     */
    @Test
    public void exactVersions() throws Exception {
        assertForRepoVersion(getRepoVersion("1.0.0", getDate(1)), "V1.0.0D1");
        assertForRepoVersion(getRepoVersion("1.0.0", getDate(2)), "V1.0.0D2");
        assertForRepoVersion(getRepoVersion("1.0.0", getDate(3)), "V1.0.0D3");
        assertForRepoVersion(getRepoVersion("1.5.0", getDate(1)), "V1.5.0D1");
        assertForRepoVersion(getRepoVersion("1.5.0", getDate(2)), "V1.5.0D2");
        assertForRepoVersion(getRepoVersion("1.5.0", getDate(3)), "V1.5.0D3");
        assertForRepoVersion(getRepoVersion("1.5.5", getDate(1)), "V1.5.5D1");
        assertForRepoVersion(getRepoVersion("1.5.5", getDate(2)), "V1.5.5D2");
        assertForRepoVersion(getRepoVersion("1.5.5", getDate(3)), "V1.5.5D3");
        assertForRepoVersion(getRepoVersion("1.6.0", getDate(1)), "V1.6.0D1");
        assertForRepoVersion(getRepoVersion("1.6.0", getDate(2)), "V1.6.0D2");
        assertForRepoVersion(getRepoVersion("1.6.0", getDate(3)), "V1.6.0D3");
        assertForRepoVersion(getRepoVersion("2.0.0", getDate(1)), "V2.0.0D1");
        assertForRepoVersion(getRepoVersion("2.0.0", getDate(2)), "V2.0.0D2");
        assertForRepoVersion(getRepoVersion("2.0.0", getDate(3)), "V2.0.0D3");
    }

    /**
     * If no date, get latest
     */
    @Test
    public void noDate() throws Exception {
        assertForRepoVersion(getRepoVersion("1.5.0", null), "V1.5.0D3");
    }

    /**
     * If no exact date, get closest before it while matching date
     */
    @Test
    public void noExactDate() throws Exception {
        assertForRepoVersion(getRepoVersion("1.5.0", getDate(2, 6)), "V1.5.0D2");
    }

    /**
     * If no version, get latest
     */
    @Test
    public void noVersion() throws Exception {
        assertForRepoVersion(getRepoVersion(null, getDate(2)), "V2.0.0D2");
    }

    /**
     * If no exact version, get closest before it, disregard date
     */
    @Test
    public void noExactVersion() throws Exception {
        assertForRepoVersion(getRepoVersion("1.10.5", getDate(2)), "V1.6.0D3");
    }

    /**
     * If no exact version get closest before it; if no date, get latest date
     */
    @Test
    public void noExactVersionNoDate() throws Exception {
        assertForRepoVersion(getRepoVersion("1.10.5", null), "V1.6.0D3");
    }


    /**
     * If no version and date, get latest
     */
    @Test
    public void noVersionNoDate() throws Exception {
        assertForRepoVersion(getRepoVersion(null, null), "V2.0.0D3");
    }


    private void assertForRepoVersion(RepositoryVersion repositoryVersion, String expectedValue) {
        Assert.assertThat(getValue(repositoryVersion), is(expectedValue));
    }

    private String getValue(RepositoryVersion version) {
        return new VersionedWebElementRepositoryImpl(version, webElementRepository).get(pageObjectName, locatorId).value;
    }

    private LocalDateTime getDate(int day) {
        return LocalDateTime.of(2017, 6, day, 0, 0);
    }

    private LocalDateTime getDate(int day, int hour) {
        return LocalDateTime.of(2017, 6, day, hour, 0);
    }

    private RepositoryVersion getRepoVersion(String implVersion, LocalDateTime implDate) {
        return new RepositoryVersion(implVersion, implDate);
    }
}