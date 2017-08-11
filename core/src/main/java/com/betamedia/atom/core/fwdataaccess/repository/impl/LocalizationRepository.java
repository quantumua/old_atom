package com.betamedia.atom.core.fwdataaccess.repository.impl;

import com.betamedia.atom.core.fwdataaccess.entities.LocalizationEntry;
import com.betamedia.atom.core.fwdataaccess.repository.util.Index;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import com.betamedia.atom.core.fwdataaccess.repository.util.RepositoryVersion;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/**
 * @author mbelyaev
 * @since 8/11/17
 */
@Repository
public class LocalizationRepository {
    private static final Logger logger = LogManager.getLogger(LocalizationRepository.class);

    private static final String DEFAULT_LOCALIZATION_PATH_PATTERN = "/i18n/*.csv";
    private static final String INITIALIZATION_ERROR_MESSAGE = "Failed to initialize localization data";

    private PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();

    private Map<Language, List<LocalizationEntry>> localizations = new EnumMap<>(Language.class);
    private String localizationPathPattern = DEFAULT_LOCALIZATION_PATH_PATTERN;

    @PostConstruct
    public void populateRepository() throws IOException {
        HeaderColumnNameMappingStrategy<LocalizationEntry> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(LocalizationEntry.class);
        CsvToBean<LocalizationEntry> csvToBean = new CsvToBean<>();
        for (Resource resource : patternResolver.getResources(localizationPathPattern)) {
            try (InputStream resourceInputStream = resource.getInputStream()) {
                localizations.put(
                        Language.valueOf(getUppercaseName(resource)),
                        csvToBean.parse(strategy, new CSVReader(new InputStreamReader(resourceInputStream))));
            } catch (IOException e) {
                logger.error(INITIALIZATION_ERROR_MESSAGE, e);
                throw new RuntimeException(INITIALIZATION_ERROR_MESSAGE);
            }
        }

    }

    public void setLocalizationPathPattern(String localizationPathPattern) {
        this.localizationPathPattern = localizationPathPattern;
    }

    /**
     * {@link RepositoryVersion} matching strategy:
     * <li>
     * <ul>-If no implementation version is provided, use latest available</ul>
     * <ul>-If no revision date provided, use latest available</ul>
     * <ul>-Both fields have lexicographical order: in case there is no exact match for requested version, use the earliest available before it</ul>
     * </li>
     */
    public Map<Index, Map<Language, String>> getVersionedLocalizations(RepositoryVersion targetVersion) {
        return localizations.entrySet().stream()
                .flatMap(e -> e.getValue().stream().map(l -> new Tuple<>(e.getKey(), l)))
                .filter(t -> t.second.getRepositoryVersion().compareTo(targetVersion) <= 0)
                .sorted(comparing(t -> t.second.getRepositoryVersion()))
                .collect(groupingBy(t -> t.second.getIndex(), toMap(
                        t -> t.first,
                        t -> t.second.getText(),
                        (u, v) -> {
                            throw new IllegalStateException(String.format("Duplicate key %s", u));
                        },
                        () -> new EnumMap<>(Language.class)
                )));
    }

    private static String getUppercaseName(Resource resource) {
        return resource.getFilename().replaceFirst("(\\.[^.]+)$", "").toUpperCase();
    }

    private static class Tuple<T1, T2> {

        public final T1 first;
        public final T2 second;

        public Tuple(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }
    }
}
