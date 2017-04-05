package com.betamedia.qe.af.testslibrary.option24.web;

import com.betamedia.qe.af.core.holders.AppContextHolder;
import com.betamedia.qe.af.core.dataprovider.DataProviderFactory;
import com.betamedia.qe.af.core.testingtype.tp.TPClientTest;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by mbelyaev on 2/28/17.
 */
public abstract class DataDrivenTPClientTest extends TPClientTest {

    @DataProvider(name = "authentication", parallel = true)
    public static Iterator<Object[]> credentials() throws IOException {
        //TODO remove file extension/design different access procedure
        DataProviderFactory dataProviderFactory = getDataProviderFactory();
        return dataProviderFactory.getData("authentication.csv");
    }

    private static DataProviderFactory getDataProviderFactory() {
        return AppContextHolder.getBean(DataProviderFactory.class);
    }
}
