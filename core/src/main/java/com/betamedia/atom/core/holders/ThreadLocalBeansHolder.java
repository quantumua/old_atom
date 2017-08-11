package com.betamedia.atom.core.holders;

import com.betamedia.atom.core.dsl.templates.tp.TPTemplate;
import com.betamedia.atom.core.fwdataaccess.repository.VersionedLocalizationRepository;
import com.betamedia.atom.core.fwdataaccess.repository.VersionedWebElementRepository;
import com.betamedia.atom.core.fwservices.webdriver.WebDriverFactory;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/13/17.
 */
public class ThreadLocalBeansHolder {
    private static final InheritableThreadLocal<WebDriverFactory> webDriverFactoryThreadLocal = new InheritableThreadLocal<>();
    private static final InheritableThreadLocal<VersionedWebElementRepository> versionedWebElementRepositoryThreadLocal = new InheritableThreadLocal<>();
    private static final InheritableThreadLocal<TPTemplate> operationsTemplateThreadLocal = new InheritableThreadLocal<>();
    private static final InheritableThreadLocal<VersionedLocalizationRepository> versionedLocalizationRepositoryThreadLocal = new InheritableThreadLocal<>();

    private ThreadLocalBeansHolder(){}

    public static WebDriverFactory getWebDriverFactory() {
        return webDriverFactoryThreadLocal.get();
    }

    public static void setWebDriverFactory(WebDriverFactory webDriverFactory) {
        webDriverFactoryThreadLocal.set(webDriverFactory);
    }

    public static VersionedWebElementRepository getVersionedWebElementRepository() {
        return versionedWebElementRepositoryThreadLocal.get();
    }

    public static void setVersionedWebElementRepository(VersionedWebElementRepository versionedWebElementRepository) {
        versionedWebElementRepositoryThreadLocal.set(versionedWebElementRepository);
    }

    public static TPTemplate getOperationsTemplate() {
        return operationsTemplateThreadLocal.get();
    }

    public static void setOperationsTemplate(TPTemplate operationsTemplate) {
        operationsTemplateThreadLocal.set(operationsTemplate);
    }

    public static VersionedLocalizationRepository getVersionedLocalizationRepository() {
        return versionedLocalizationRepositoryThreadLocal.get();
    }

    public static void setVersionedLocalizationRepository(VersionedLocalizationRepository versionedLocalizationRepository) {
        versionedLocalizationRepositoryThreadLocal.set(versionedLocalizationRepository);
    }
}
