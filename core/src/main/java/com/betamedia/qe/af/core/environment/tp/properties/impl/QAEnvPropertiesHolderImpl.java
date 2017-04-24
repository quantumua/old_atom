package com.betamedia.qe.af.core.environment.tp.properties.impl;

import com.betamedia.qe.af.core.environment.tp.QAEnvironment;
import com.betamedia.qe.af.core.environment.tp.properties.AbstractEnvPropertiesHolder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/14/17.
 */
@Component
@PropertySource("qa-env.properties")
public class QAEnvPropertiesHolderImpl extends AbstractEnvPropertiesHolder implements QAEnvironment {

}