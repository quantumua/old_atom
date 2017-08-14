package com.betamedia.atom.core.dsl.pages.localization;

import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import org.openqa.selenium.By;

import java.util.Map; /**
 * @author mbelyaev
 * @since 8/14/17
 */
public interface LocalizationStorage {
    void put(By locator, Map<Language, String> languageStringMap);

    Map<Language, String> get(By locator);
}
