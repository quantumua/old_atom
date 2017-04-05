package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.common.search.criteria.SearchCriteria;
import com.betamedia.qe.af.core.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.dsl.operations.*;
import com.betamedia.tp.api.model.AccountGroup;
import com.betamedia.tp.api.model.Option;
import com.betamedia.tp.api.model.OptionTemplate;
import com.betamedia.tp.api.model.enums.OptionStatus;
import com.betamedia.tp.api.model.enums.OptionType;
import com.betamedia.tp.api.service.IOptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by mbelyaev on 3/24/17.
 */
@Component
public class OptionOperationsImpl implements OptionOperations {
    private static final Logger logger = LogManager.getLogger(OptionOperationsImpl.class);

    @Autowired
    private AFTPConnector tpConnector;
    @Autowired
    private AccountGroupOperations accountGroupOperations;
    @Autowired
    private OptionTemplateOperations optionTemplateOperations;

    @Override
    public Option issue(String assetId, OptionType type, TagOperations.TagName tag) {
        AccountGroup accountGroup = accountGroupOperations.get();
        List<OptionTemplate> templates = optionTemplateOperations.getOptionTemplates(assetId, accountGroup, type, tag);
        if (templates.isEmpty()) {
            templates = Collections.singletonList(optionTemplateOperations.create(assetId, accountGroup, type, tag));
        }
        long openTime = System.currentTimeMillis();
        long closeTime = openTime + 1000 * 60;
        tpConnector.serviceProxy(IOptionService.class)
                .createOption(assetId, templates.get(0).getId(), openTime, closeTime);
        return findOption(forStatusAndCloseTimeAndTemplate(OptionStatus.OPEN, closeTime, templates.get(0).getId())).get(0);
    }

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
