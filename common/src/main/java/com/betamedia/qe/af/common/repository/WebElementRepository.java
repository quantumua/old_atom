package com.betamedia.qe.af.common.repository;

import com.betamedia.qe.af.common.entities.FindBy;
import com.betamedia.qe.af.common.entities.PageElementLocation;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mbelyaev on 2/16/17.
 */
@Repository
public class WebElementRepository {

    private List<PageElementLocation> pageElements = null;

    @PostConstruct
    public void populateRepository() throws IOException {


        HeaderColumnNameMappingStrategy<PageElementLocation> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(PageElementLocation.class);
        CsvToBean<PageElementLocation> csvToBean = new CsvToBean<>();
        try (InputStream resourceInputStream = new ClassPathResource("/pageElementLocations.csv").getInputStream();) {
            pageElements = csvToBean.parse(strategy, new CSVReader(new InputStreamReader(resourceInputStream)));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize web elements IDs store");
        }
    }

    public Map<Index, FindBy> getVersionedWebElements(String version) {
        return getPageElements().filter(el -> matchesAppVersion(el, version))
                .collect(Collectors.toMap(Index::new, el -> new FindBy(el.getHow(), el.getValue())));
    }

    private boolean matchesAppVersion(PageElementLocation el, String targetVersion) {
        return versionCompare(el.getVersion(), targetVersion) <= 0;
    }

    public Stream<PageElementLocation> getPageElements() {
        return pageElements.stream();
    }

    /**
     * Compares two version strings.
     * <p>
     * Use this instead of String.compareTo() for a non-lexicographical
     * comparison that works for version strings. e.g. "1.10".compareTo("1.6").
     *
     * @param str1 a string of ordinal numbers separated by decimal points.
     * @param str2 a string of ordinal numbers separated by decimal points.
     * @return The result is a negative integer if str1 is _numerically_ less than str2.
     * The result is a positive integer if str1 is _numerically_ greater than str2.
     * The result is zero if the strings are _numerically_ equal.
     * @note It does not work if "1.10" is supposed to be equal to "1.10.0".
     */
    private static int versionCompare(String str1, String str2) {
        String[] vals1 = str1.split("\\.");
        String[] vals2 = str2.split("\\.");
        int i = 0;
        // set index to first non-equal ordinal or length of shortest version string
        while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
            i++;
        }
        // compare first non-equal ordinal number
        if (i < vals1.length && i < vals2.length) {
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        }
        // the strings are equal or one string is a substring of the other
        // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
        return Integer.signum(vals1.length - vals2.length);
    }

    public static class Index {
        private String pageObjectName;
        private String elementId;

        Index(String pageObjectName, String elementId) {
            this.pageObjectName = pageObjectName;
            this.elementId = elementId;
        }

        Index(PageElementLocation pageElementLocation) {
            this.pageObjectName = pageElementLocation.getPageObjectName();
            this.elementId = pageElementLocation.getElementId();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Index index = (Index) o;
            return Objects.equals(pageObjectName, index.pageObjectName) &&
                    Objects.equals(elementId, index.elementId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pageObjectName, elementId);
        }
    }

}
