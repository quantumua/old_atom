package com.betamedia.atom.core.fwdataaccess.repository.impl;

import com.betamedia.atom.core.fwdataaccess.repository.ResourceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mbelyaev on 4/18/17.
 */
@Component
public class ResourceRepositoryImpl implements ResourceRepository {
    private static final Logger logger = LogManager.getLogger(ResourceRepositoryImpl.class);

    private Map<String, List<String>> fileContents;

    @PostConstruct
    public void populateRepository() {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(this.getClass().getClassLoader());
        try {
            fileContents = Arrays.stream(resolver.getResources("classpath:/data/*.txt"))
                    .collect(Collectors.toConcurrentMap(
                            Resource::getFilename,
                            ResourceRepositoryImpl::getContents
                    ));
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    private static List<String> getContents(Resource resource) {
        try {
            return new BufferedReader(new InputStreamReader(resource.getInputStream())).lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }

    @Override
    public List<String> get(String name) {
        return fileContents.get(name);
    }

    @Override
    public void store(String name, List<String> content) {
        fileContents.put(name, content);
    }
}

