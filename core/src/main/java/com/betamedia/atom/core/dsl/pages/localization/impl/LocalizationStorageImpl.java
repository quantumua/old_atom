package com.betamedia.atom.core.dsl.pages.localization.impl;

import com.betamedia.atom.core.dsl.pages.localization.LocalizationStorage;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import org.openqa.selenium.By;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author mbelyaev
 * @since 8/14/17
 */

public class LocalizationStorageImpl implements LocalizationStorage {
    private Map<By, Map<Language, String>> localizations = new HashMap<>();

    @Override
    public void put(By locator, Map<Language, String> languageStringMap) {
        localizations.put(locator, languageStringMap);
    }

    @Override
    public Map<Language, String> get(By locator) {
        return Optional.ofNullable(localizations.get(locator)).map(Collections::unmodifiableMap).orElse(Collections.emptyMap());
    }
}
