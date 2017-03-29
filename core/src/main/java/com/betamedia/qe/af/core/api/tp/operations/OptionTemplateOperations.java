package com.betamedia.qe.af.core.api.tp.operations;

import com.betamedia.qe.af.core.api.tp.operations.impl.TagOperationsImpl;
import com.betamedia.tp.api.model.AccountGroup;
import com.betamedia.tp.api.model.OptionTemplate;
import com.betamedia.tp.api.model.enums.OptionSubType;
import com.betamedia.tp.api.model.enums.OptionType;

import java.util.List;

/**
 * Created by mbelyaev on 3/24/17.
 */
public interface OptionTemplateOperations {
    OptionTemplate get();

    OptionTemplate get(String id);

    List<OptionTemplate> getOptionTemplates(String assetId, AccountGroup accountGroup, OptionType type, TagOperations.TagName tag);

    OptionTemplate create(String assetId, AccountGroup accountGroup, OptionType type, TagOperations.TagName tag);
}