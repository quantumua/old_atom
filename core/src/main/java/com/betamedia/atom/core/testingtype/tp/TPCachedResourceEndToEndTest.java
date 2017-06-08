package com.betamedia.atom.core.testingtype.tp;

import com.betamedia.atom.core.fwdataaccess.repository.EntityRepository;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.testingtype.base.AbstractTest;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.List;

/**
 * @author mbelyaev
 * @since 4/18/17
 */
public abstract class TPCachedResourceEndToEndTest extends TPEndToEndTest {
    protected static final String CACHED_DATA_PROVIDER = "CachedDataProvider";
    protected static final String CACHED_PARALLEL_DATA_PROVIDER = "CachedParallelDataProvider";

    private EntityRepository entityRepository = null;

    private EntityRepository getEntityRepository() {
        if (entityRepository == null) {
            entityRepository = AppContextHolder.getBean(EntityRepository.class);
        }
        return entityRepository;
    }

    protected final <T> List<T> getResources(Class<T> entity) {
        return getEntityRepository().get(entity);
    }

    /**
     * Generic data provider that attempts to get resources from {@link EntityRepository}
     * as entities provided by {@link AbstractTest#getDataSourceEntity()};
     */
    @DataProvider(name = CACHED_DATA_PROVIDER)
    public final Iterator<Object[]> cachedDataProvider() {
        return getResources(getDataSourceEntity())
                .stream()
                .map(a -> new Object[]{a})
                .iterator();
    }

    /**
     * Generic data provider that attempts to get resources from {@link EntityRepository}
     * as entities provided by {@link AbstractTest#getDataSourceEntity()};
     *
     * @implNote TestNG will invoke {@link #runTearDown()} after every execution
     */
    @DataProvider(name = CACHED_PARALLEL_DATA_PROVIDER, parallel = true)
    public final Iterator<Object[]> cachedParallelDataProvider() {
        return getResources(getDataSourceEntity())
                .stream()
                .map(a -> new Object[]{a})
                .iterator();
    }
}
