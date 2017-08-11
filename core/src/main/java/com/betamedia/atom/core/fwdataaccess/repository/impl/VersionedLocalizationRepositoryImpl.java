package com.betamedia.atom.core.fwdataaccess.repository.impl;

import com.betamedia.atom.core.fwdataaccess.repository.VersionedLocalizationRepository;
import com.betamedia.atom.core.fwdataaccess.repository.util.Index;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import com.betamedia.atom.core.fwdataaccess.repository.util.RepositoryVersion;

import java.util.Map;

/**
 * @author Maksym Tsybulskyy
 * Date: 3/15/17.
 */
public class VersionedLocalizationRepositoryImpl implements VersionedLocalizationRepository {

    private Map<Index, Map<Language, String>> localizations = null;

    public VersionedLocalizationRepositoryImpl(RepositoryVersion version, LocalizationRepository localizationRepository) {
        localizations = localizationRepository.getVersionedLocalizations(version);
    }

    @Override
    public Map<Language, String> get(String pageObjectName, String locatorId) {
        return localizations.get(new Index(pageObjectName, locatorId));
    }
}
