package com.betamedia.atom.core.fwdataaccess.repository;

import com.betamedia.atom.core.fwdataaccess.repository.util.Language;

import java.util.Map;

/**
 * @author Maksym Tsybulskyy
 * Date: 3/15/17.
 */
public interface VersionedLocalizationRepository {
    Map<Language, String> get(String pageObjectName, String locatorId);
}
