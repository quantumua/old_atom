package com.betamedia.qe.af.core.environment.tp.properties.impl;

import com.betamedia.qe.af.core.environment.tp.AutomationEnvironment;
import com.betamedia.qe.af.core.environment.tp.properties.AbstractEnvPropertiesHolder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/14/17.
 */
@Component
@PropertySource("/env/automation-env.properties")
public class AutomationEnvPropertiesHolderImpl extends AbstractEnvPropertiesHolder<AutomationEnvironment> implements AutomationEnvironment {
}
