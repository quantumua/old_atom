package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.common.search.criteria.SearchCriteria;
import com.betamedia.qe.af.core.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.dsl.operations.OptionOperations;
import com.betamedia.tp.api.model.Option;
import com.betamedia.tp.api.model.enums.OptionStatus;
import com.betamedia.tp.api.service.IOptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * This class was designed to provide means to execute option operations, but now it is deprecated.
 *
 * Created by mbelyaev on 3/24/17.
 *
 * @deprecated Replaced by {@link OptionTemplateOperationsImpl}
 * @see Option
 */
@Component
public class OptionOperationsImpl implements OptionOperations {
    private static final Logger logger = LogManager.getLogger(OptionOperationsImpl.class);

    @Autowired
    private AFTPConnector tpConnector;

    private List<Option> findOption(SearchCriteria<Option> criteria) {
        return tpConnector.serviceProxy(IOptionService.class)
                .readMultiple(criteria, null, null)
                .getContent();
    }

    private SearchCriteria<Option> forStatusAndCloseTimeAndTemplate(OptionStatus status, long closeTime, String templateId) {
        return new SearchCriteria<>(Option.class)
                .and(Option.EP_STATUS.equalsTo(status))
                .and(Option.EP_CLOSE_TIME.equalsTo(closeTime))
                .and(Option.EP_TEMPLATE_ID.equalsTo(templateId));
    }
}
