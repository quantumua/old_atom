package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.common.search.criteria.SearchCriteria;
import com.betamedia.qe.af.core.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.api.tp.operations.TagOperations;
import com.betamedia.tp.api.model.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mbelyaev on 3/27/17.
 */
@Component
public class TagOperationsImpl implements TagOperations {
    private static final Logger logger = LogManager.getLogger(TagOperationsImpl.class);

    @Autowired
    private AFTPConnector tpConnector;

    @Override
    public List<Tag> get(TagName tagName) {
        return tpConnector.readMultiple(forTagName(tagName), null, null).getContent();
    }

    private SearchCriteria<Tag> forTagName(TagName tagName) {
        return new SearchCriteria<>(Tag.class)
                .and(Tag.EP_TAG_NAME.equalsTo(tagName.value));
    }

}
