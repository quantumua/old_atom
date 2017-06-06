package com.betamedia.atom.core.testingtype.tp;

import com.betamedia.atom.core.fwdataaccess.repository.EntityRepository;
import com.betamedia.atom.core.holders.AppContextHolder;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.List;

/**
 * @author mbelyaev
 * @since 4/18/17
 */
public abstract class TPCachedResourceEndToEndTest extends TPEndToEndTest {
    private EntityRepository entityRepository = null;

    private EntityRepository getEntityRepository() {
        if (entityRepository == null) {
            entityRepository = AppContextHolder.getBean(EntityRepository.class);
        }
        return entityRepository;
    }

    public final <T> List<T> getResources(Class<T> entity) {
        return getEntityRepository().get(entity);
    }

    @DataProvider(name = "CachedDataProvider", parallel = true)
    public final Iterator<Object[]> cachedDataProvider() {
        return getResources(getDataSourceEntity())
                .stream()
                .map(a -> new Object[]{a})
                .iterator();
    }
}
