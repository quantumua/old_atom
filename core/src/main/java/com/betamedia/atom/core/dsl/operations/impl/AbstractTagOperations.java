package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.dsl.operations.TagOperations;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.common.search.criteria.SearchCriteria;
import com.betamedia.tp.api.model.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * This class is designed to facilitate the execution of common operations related to tag operations.
 * It can be used as a "building block" when writing integration tests.
 * <p>
 * Created by mbelyaev on 3/27/17.
 *
 * @see Tag
 */
public abstract class AbstractTagOperations<T extends EnvironmentDependent> implements TagOperations<T> {
    private static final Logger logger = LogManager.getLogger(AbstractTagOperations.class);

    @Autowired
    private FWTPConnector<T> tpConnector;

    /**
     * Returns tag for a given tag name.
     */
    @Override
    public List<Tag> get(TagName tagName) {
        return tpConnector.readMultiple(forTagName(tagName), null, null).getContent();
    }

    private SearchCriteria<Tag> forTagName(TagName tagName) {
        return new SearchCriteria<>(Tag.class)
                .and(Tag.EP_TAG_NAME.equalsTo(tagName.value));
    }

}
