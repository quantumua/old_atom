package com.betamedia.atom.core.environment.tp.properties.impl;

import com.betamedia.atom.core.environment.tp.properties.AbstractEnvPropertiesHolder;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/14/17.
 */
@Component
@PropertySource("/env/qa-env.properties")
public class QAEnvPropertiesHolderImpl extends AbstractEnvPropertiesHolder implements QAEnvironment {

}
