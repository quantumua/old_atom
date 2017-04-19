package com.betamedia.qe.af.core.fwdataaccess.repository.impl;

import com.betamedia.qe.af.core.fwdataaccess.entities.FindBy;
import com.betamedia.qe.af.core.fwdataaccess.repository.VersionedWebElementRepository;

import java.util.Map;

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
        return locations.get(new WebElementRepository.Index(pageObjectName, locatorId));
    }
}
