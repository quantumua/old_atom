package com.betamedia.atom.core.dsl.templates.tp;

import com.betamedia.atom.core.dsl.type.EnvironmentType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/24/17.
 */
public interface TPTemplateProvider {
    TPTemplate get(EnvironmentType env);
}
