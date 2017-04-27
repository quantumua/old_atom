package com.betamedia.qe.af.core.fwdataaccess.repository.impl;

import com.betamedia.qe.af.core.fwdataaccess.annotations.ClasspathLocation;
import com.betamedia.qe.af.core.fwdataaccess.repository.EntityRepository;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mbelyaev on 4/27/17.
 */
@Component
public class EntityRepositoryImpl implements EntityRepository {
    private static final Logger logger = LogManager.getLogger(EntityRepositoryImpl.class);

    private Map<Class, List> repository = new ConcurrentHashMap<>();

    @Override
    public <T> List<T> get(Class<T> entity) {
        if (!repository.containsKey(entity)) {
            repository.putIfAbsent(entity, generateContents(entity));
        }
        return repository.get(entity);
    }

    private <T> List<T> generateContents(Class<T> entity) {
        List<T> contents = Collections.emptyList();
        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(entity);
        CsvToBean<T> csvToBean = new CsvToBean<>();
        try (InputStream resourceInputStream = new ClassPathResource(entity.getAnnotation(ClasspathLocation.class).value()).getInputStream()) {
            contents = csvToBean.parse(strategy, new CSVReader(new InputStreamReader(resourceInputStream)));
        } catch (IOException e) {
            logger.error("",e);
            throw new RuntimeException("Failed to populate repository for " + entity);
        }
        return contents;
    }
}
