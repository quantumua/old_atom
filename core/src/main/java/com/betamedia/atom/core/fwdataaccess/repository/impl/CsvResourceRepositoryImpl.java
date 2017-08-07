package com.betamedia.atom.core.fwdataaccess.repository.impl;

import com.betamedia.atom.core.fwdataaccess.annotations.ClasspathLocation;
import com.betamedia.atom.core.fwdataaccess.entities.ExpectedCfdAsset;
import com.betamedia.atom.core.fwdataaccess.repository.CsvResourceRepository;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mbelyaev on 4/27/17.
 */
@Component
public class CsvResourceRepositoryImpl implements CsvResourceRepository {
    private static final Logger logger = LogManager.getLogger(CsvResourceRepositoryImpl.class);

    private Map<Class, List> repository = new ConcurrentHashMap<>();

    @Override
    public <T> List<T> get(Class<T> entity) {
        return repository.computeIfAbsent(entity, this::getDefaultContents);
    }

    @Override
    public void updateExpectedCfdAssets(MultipartFile expectedCfdAssets){
        try(InputStream inputStream = expectedCfdAssets.getInputStream()){
            repository.put(ExpectedCfdAsset.class, parseToEntities(ExpectedCfdAsset.class, inputStream));
        } catch (IOException e) {
            logger.error("",e);
            throw new RuntimeException("Failed to update repository for ExpectedCfdAssets");
        }
    }

    private <T> List<T> getDefaultContents(Class<T> entity) {
        try (InputStream inputStream = new ClassPathResource(entity.getAnnotation(ClasspathLocation.class).value()).getInputStream()) {
            return parseToEntities(entity, inputStream);
        } catch (IOException e) {
            logger.error("",e);
            throw new RuntimeException("Failed to get contents for " + entity);
        }
    }

    private <T> List<T> parseToEntities(Class<T> entity, InputStream inputStream) {
        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(entity);
        CsvToBean<T> csvToBean = new CsvToBean<>();
        return csvToBean.parse(strategy, new CSVReader(new InputStreamReader(inputStream)));
    }
}
