package com.betamedia.qe.af.common.holder;

import com.betamedia.qe.af.common.factory.webdriver.WebDriverFactory;
import com.betamedia.qe.af.common.repository.VersionedWebElementRepository;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/13/17.
 */
public class ThreadLocalBeansHolder {
    private static final InheritableThreadLocal<WebDriverFactory> webDriverFactoryThreadLocal = new InheritableThreadLocal<>();
    private static final InheritableThreadLocal<VersionedWebElementRepository> versionedWebElementRepositoryThreadLocal = new InheritableThreadLocal<>();

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
}
