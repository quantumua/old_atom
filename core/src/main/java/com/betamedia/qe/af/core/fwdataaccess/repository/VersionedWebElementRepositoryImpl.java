package com.betamedia.qe.af.core.fwdataaccess.repository;

import com.betamedia.qe.af.core.fwdataaccess.entities.FindBy;

import java.util.Map;

import static com.betamedia.qe.af.core.fwdataaccess.repository.WebElementRepository.Index;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/15/17.
 */
public class VersionedWebElementRepositoryImpl implements VersionedWebElementRepository {

    private Map<WebElementRepository.Index, FindBy> locations = null;

    public VersionedWebElementRepositoryImpl(String version, WebElementRepository webElementRepository) {
        locations = webElementRepository.getVersionedWebElements(version);
    }

    @Override
    public FindBy get(String pageObjectName, String locatorId) {
        return locations.get(new Index(pageObjectName, locatorId));
    }
}
