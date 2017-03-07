package com.betamedia.qe.af.tests;

import com.betamedia.qe.af.components.DataProviderFactory;
import com.betamedia.qe.af.configuration.AppContextHolder;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by mbelyaev on 2/28/17.
 */
public abstract class DataDrivenTPWebDriverTest extends TPWebDriverTest {

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
