package com.betamedia.qe.af.core.testingtype.base;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.core.io.ClassPathResource;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mbelyaev on 5/23/17.
 */
public abstract class AbstractTest {

    @DataProvider(name = "GenericDataProvider")
    public Iterator<Object[]> genericDataProvider() {
        return getData(getDataSourceEntity(), getDataSourcePath())
                .stream()
                .map(d -> new Object[]{d})
                .iterator();
    }

    protected Class getDataSourceEntity() {
        return null;
    }

    protected String getDataSourcePath() {
        return null;
    }

    private static <T> List<T> getData(Class<T> tClass, String path) {
        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(tClass);
        CsvToBean<T> csvToBean = new CsvToBean<>();
        try (InputStream inputStream = new ClassPathResource(path).getInputStream()) {
            return csvToBean.parse(strategy, new CSVReader(new InputStreamReader(inputStream)));
        } catch (IOException e) {
            Reporter.log("Failed to get contents for questionnaire data");
            throw new RuntimeException("Failed to get contents for questionnaire data");
        }
    }
}