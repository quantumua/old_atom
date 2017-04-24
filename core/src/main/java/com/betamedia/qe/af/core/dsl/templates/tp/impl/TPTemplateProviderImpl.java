package com.betamedia.qe.af.core.dsl.templates.tp.impl;

import com.betamedia.qe.af.core.dsl.pages.type.EnvironmentType;
import com.betamedia.qe.af.core.dsl.templates.tp.TPTemplate;
import com.betamedia.qe.af.core.dsl.templates.tp.TPTemplateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/24/17.
 */
@Component
public class TPTemplateProviderImpl implements TPTemplateProvider {

    private Map<EnvironmentType, TPTemplate> templates;

    @Override
    public TPTemplate get(EnvironmentType env) {
        return templates.get(env);
    }

    @Autowired
    private void init(List<TPTemplate> templateList) {
        templates = templateList.stream().collect(Collectors.toMap(TPTemplate::getEnvironment, template -> template));
    }
}
