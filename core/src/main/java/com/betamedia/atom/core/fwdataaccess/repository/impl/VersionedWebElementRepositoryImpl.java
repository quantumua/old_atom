package com.betamedia.atom.core.fwdataaccess.repository.impl;

import com.betamedia.atom.core.fwdataaccess.repository.VersionedWebElementRepository;
import com.betamedia.atom.core.fwdataaccess.repository.util.Index;
import com.betamedia.atom.core.fwdataaccess.repository.util.RepositoryVersion;
import com.betamedia.atom.core.fwdataaccess.entities.FindBy;

import java.util.Map;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/15/17.
 */
public class VersionedWebElementRepositoryImpl implements VersionedWebElementRepository {

    private Map<Index, FindBy> locations = null;

    public VersionedWebElementRepositoryImpl(RepositoryVersion version, WebElementRepository webElementRepository) {
        locations = webElementRepository.getVersionedWebElements(version);
    }

    @Override
    public FindBy get(String pageObjectName, String locatorId) {
        return locations.get(new Index(pageObjectName, locatorId));
    }
}
