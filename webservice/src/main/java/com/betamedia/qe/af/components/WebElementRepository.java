package com.betamedia.qe.af.components;

import com.betamedia.qe.af.entities.page.PageElementLocation;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by mbelyaev on 2/16/17.
 */
@Repository
public class WebElementRepository {

    private Map<Index, String> locations = new HashMap<>();

    @PostConstruct
    public void populateRepository() {
        HeaderColumnNameMappingStrategy<PageElementLocation> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(PageElementLocation.class);
        CsvToBean<PageElementLocation> csvToBean = new CsvToBean<>();
        try (InputStream resourceInputStream = new ClassPathResource("/pageElementLocations.csv").getInputStream();) {
            csvToBean.parse(strategy, new CSVReader(new InputStreamReader(resourceInputStream))).forEach(el ->
                    locations.put(
                            new Index(el.getPageObjectName(),
                                    el.getElementId(),
                                    el.getVersion()),
                            el.getId())
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize web elements IDs store");
        }
    }

    public String getId(String pageObjectName, String elementId) {
        SUTPropertiesHolder holder = (SUTPropertiesHolder) RequestContextHolder.getRequestAttributes().getAttribute("sutPropertyHolder", RequestAttributes.SCOPE_REQUEST);
        String version = holder.get(SUTPropertiesHolder.ID_VERSION);
        return locations.get(new Index(pageObjectName, elementId, version));
    }

    private class Index {
        private String pageObjectName;
        private String elementId;
        private String version;

        public Index(String pageObjectName, String elementId, String version) {
            this.pageObjectName = pageObjectName;
            this.elementId = elementId;
            this.version = version;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Index index = (Index) o;
            return Objects.equals(pageObjectName, index.pageObjectName) &&
                    Objects.equals(elementId, index.elementId) &&
                    Objects.equals(version, index.version);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pageObjectName, elementId, version);
        }
    }

}
