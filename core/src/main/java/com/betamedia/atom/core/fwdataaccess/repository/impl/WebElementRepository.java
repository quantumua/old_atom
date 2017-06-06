package com.betamedia.atom.core.fwdataaccess.repository.impl;

import com.betamedia.atom.core.fwdataaccess.repository.util.Index;
import com.betamedia.atom.core.fwdataaccess.entities.FindBy;
import com.betamedia.atom.core.fwdataaccess.entities.PageElementLocation;
import com.betamedia.atom.core.fwdataaccess.repository.util.RepositoryVersion;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;

/**
 * @author mbelyaev
 * @since 2/16/17
 */
@Repository
public class WebElementRepository {
    private static final Logger logger = LogManager.getLogger(WebElementRepository.class);

    private static final String DEFAULT_WEB_OBJECTS_PATH = "/webobjects/webObjectRepository.csv";
    private static final String INITIALIZATION_ERROR_MESSAGE = "Failed to initialize web element ID storage";

    private List<PageElementLocation> pageElements = null;
    private String webObjectsPath = DEFAULT_WEB_OBJECTS_PATH;

    @PostConstruct
    public void populateRepository() throws IOException {
        HeaderColumnNameMappingStrategy<PageElementLocation> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(PageElementLocation.class);
        CsvToBean<PageElementLocation> csvToBean = new CsvToBean<>();
        try (InputStream resourceInputStream = new ClassPathResource(webObjectsPath).getInputStream();) {
            pageElements = csvToBean.parse(strategy, new CSVReader(new InputStreamReader(resourceInputStream)));
        } catch (IOException e) {
            logger.error(INITIALIZATION_ERROR_MESSAGE, e);
            throw new RuntimeException(INITIALIZATION_ERROR_MESSAGE);
        }
    }

    public void setWebObjectsPath(String webObjectsPath) {
        this.webObjectsPath = webObjectsPath;
    }

    /**
     * {@link RepositoryVersion} matching strategy:
     * <li>
     * <ul>-If no implementation version is provided, use latest available</ul>
     * <ul>-If no revision date provided, use latest available</ul>
     * <ul>-Both fields have lexicographical order: in case there is no exact match for requested version, use the earliest available before it</ul>
     * </li>
     */

    public Map<Index, FindBy> getVersionedWebElements(RepositoryVersion targetVersion) {
        return pageElements.stream()
                .filter(p -> p.getRepositoryVersion().compareTo(targetVersion) <= 0)
                .sorted(comparing(PageElementLocation::getRepositoryVersion))
                .collect(toMap(PageElementLocation::getIndex, PageElementLocation::getFindBy, (findBy, findBy2) -> findBy2));
    }


}
