package com.betamedia.atom.core.fwdataaccess.repository.util;

import java.util.Objects;

/**
 * @author mbelyaev
 * @since 5/31/17
 */
public class Index {
    private final String pageObjectName;
    private final String locatorId;

    public Index(String pageObjectName, String locatorId) {
        this.pageObjectName = pageObjectName;
        this.locatorId = locatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return Objects.equals(pageObjectName, index.pageObjectName) &&
                Objects.equals(locatorId, index.locatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageObjectName, locatorId);
    }
}
