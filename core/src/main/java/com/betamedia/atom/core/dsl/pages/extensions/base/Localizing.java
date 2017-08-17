package com.betamedia.atom.core.dsl.pages.extensions.base;

import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import org.openqa.selenium.By;

/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface Localizing {
    String getLocalization(By key, Language language);
}
