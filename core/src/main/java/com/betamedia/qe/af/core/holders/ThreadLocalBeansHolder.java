package com.betamedia.qe.af.core.holders;

import com.betamedia.qe.af.core.dsl.templates.tp.TPTemplate;
import com.betamedia.qe.af.core.fwdataaccess.repository.VersionedWebElementRepository;
import com.betamedia.qe.af.core.fwservices.webdriver.WebDriverFactory;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/13/17.
 */
public class ThreadLocalBeansHolder {
    private static final InheritableThreadLocal<WebDriverFactory> webDriverFactoryThreadLocal = new InheritableThreadLocal<>();
    private static final InheritableThreadLocal<VersionedWebElementRepository> versionedWebElementRepositoryThreadLocal = new InheritableThreadLocal<>();
    private static final InheritableThreadLocal<TPTemplate> operationsTemplateThreadLocal = new InheritableThreadLocal<>();

    private ThreadLocalBeansHolder(){}

    public static WebDriverFactory getWebDriverFactoryThreadLocal() {
        return webDriverFactoryThreadLocal.get();
    }

    public static void setWebDriverFactoryThreadLocal(WebDriverFactory webDriverFactory) {
        webDriverFactoryThreadLocal.set(webDriverFactory);
    }

    public static VersionedWebElementRepository getVersionedWebElementRepositoryThreadLocal() {
        return versionedWebElementRepositoryThreadLocal.get();
    }

    public static void setVersionedWebElementRepositoryThreadLocal(VersionedWebElementRepository versionedWebElementRepository) {
        versionedWebElementRepositoryThreadLocal.set(versionedWebElementRepository);
    }

    public static TPTemplate getOperationsTemplateThreadLocal() {
        return operationsTemplateThreadLocal.get();
    }

    public static void setOperationsTemplateThreadLocal(TPTemplate operationsTemplate){
        operationsTemplateThreadLocal.set(operationsTemplate);
    }
}
