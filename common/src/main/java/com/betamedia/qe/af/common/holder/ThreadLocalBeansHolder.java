package com.betamedia.qe.af.common.holder;

import com.betamedia.qe.af.common.factory.webdriver.WebDriverFactory;
import com.betamedia.qe.af.common.repository.VersionedWebElementRepository;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/13/17.
 */
public class ThreadLocalBeansHolder {
    private final static InheritableThreadLocal<WebDriverFactory> webDriverFactoryThreadLocal = new InheritableThreadLocal<>();
    private final static InheritableThreadLocal<VersionedWebElementRepository> versionedWebElementRepositoryThreadLocal = new InheritableThreadLocal<>();

    public final static WebDriverFactory getWebDriverFactoryThreadLocal() {
        return webDriverFactoryThreadLocal.get();
    }

    public final static void setWebDriverFactoryThreadLocal(WebDriverFactory webDriverFactory) {
        webDriverFactoryThreadLocal.set(webDriverFactory);
    }

    public final static VersionedWebElementRepository getVersionedWebElementRepositoryThreadLocal() {
        return versionedWebElementRepositoryThreadLocal.get();
    }

    public final static void setVersionedWebElementRepositoryThreadLocal(VersionedWebElementRepository versionedWebElementRepository) {
        versionedWebElementRepositoryThreadLocal.set(versionedWebElementRepository);
    }
}
