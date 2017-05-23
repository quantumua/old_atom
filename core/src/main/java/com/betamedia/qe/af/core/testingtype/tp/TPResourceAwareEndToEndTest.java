package com.betamedia.qe.af.core.testingtype.tp;

import com.betamedia.qe.af.core.fwdataaccess.entities.ExpectedCfdAsset;
import com.betamedia.qe.af.core.fwdataaccess.repository.EntityRepository;
import com.betamedia.qe.af.core.holders.AppContextHolder;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.List;

/**
 * Created by mbelyaev on 4/18/17.
 */
public class TPResourceAwareEndToEndTest extends TPEndToEndTest {
    private EntityRepository entityRepository = null;

    private EntityRepository getEntityRepository() {
        if (entityRepository == null) {
            entityRepository = AppContextHolder.getBean(EntityRepository.class);
        }
        return entityRepository;
    }

    public <T> List<T> getResources(Class<T> entity) {
        return getEntityRepository().get(entity);
    }

    @DataProvider(name = "ExpectedCfdAssetDataProvider")
    public Iterator<Object[]> getExpectedCfdAssets() {
        return getResources(ExpectedCfdAsset.class)
                .stream()
                .map(a -> new Object[]{a.getListBidderName(), a.getSymbol(), a.getTooltipName()})
                .iterator();
    }
}
