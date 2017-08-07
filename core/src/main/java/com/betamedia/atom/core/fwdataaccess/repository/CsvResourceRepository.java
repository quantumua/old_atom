package com.betamedia.atom.core.fwdataaccess.repository;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by mbelyaev on 4/27/17.
 */
public interface CsvResourceRepository {

    String DATA_PROVIDER_ERROR = "Failed to load data";

    <T> List<T> get(Class<T> entity);

    void updateExpectedCfdAssets(MultipartFile expectedCfdAssets);

    static <T> List<T> getData(Class<T> tClass, String path) {
        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(tClass);
        CsvToBean<T> csvToBean = new CsvToBean<>();
        try (InputStream inputStream = new ClassPathResource(path).getInputStream()) {
            return csvToBean.parse(strategy, new CSVReader(new InputStreamReader(inputStream)));
        } catch (IOException e) {
            throw new RuntimeException(DATA_PROVIDER_ERROR, e);
        }
    }
}
