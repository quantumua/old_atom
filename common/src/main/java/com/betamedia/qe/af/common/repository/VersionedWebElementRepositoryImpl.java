package com.betamedia.qe.af.common.repository;

import java.util.Map;

import static com.betamedia.qe.af.common.repository.WebElementRepository.Index;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/15/17.
 */
public class VersionedWebElementRepositoryImpl implements VersionedWebElementRepository {

    private Map<WebElementRepository.Index, String> locations = null;

    public VersionedWebElementRepositoryImpl(String version, WebElementRepository webElementRepository) {
        locations = webElementRepository.getVersionedWebElements(version);
    }

    @Override
    public String getId(String pageObjectName, String elementId) {
        return locations.get(new Index(pageObjectName, elementId));
    }
}
