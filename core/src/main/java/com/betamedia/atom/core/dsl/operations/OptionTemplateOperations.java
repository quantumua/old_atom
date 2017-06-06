package com.betamedia.atom.core.dsl.operations;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.OptionTemplate;
import com.betamedia.tp.api.model.enums.OptionType;

import java.util.List;

/**
 * Created by mbelyaev on 3/24/17.
 */
public interface OptionTemplateOperations<T extends EnvironmentDependent> extends EnvironmentDependent {
    OptionTemplate get();

    OptionTemplate get(String id);

    List<OptionTemplate> getOptionTemplates(String assetId, OptionType type, TagOperations.TagName tag);

    OptionTemplate create(String assetId, OptionType type, TagOperations.TagName tag);

}
