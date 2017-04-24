package com.betamedia.qe.af.core.dsl.templates.tp;

import com.betamedia.qe.af.core.dsl.pages.type.EnvironmentType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/24/17.
 */
public interface TPTemplateProvider {
    TPTemplate get(EnvironmentType env);
}
