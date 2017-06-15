package com.betamedia.atom.core.environment.tp.properties.impl;

import com.betamedia.atom.core.environment.tp.NewAutomationEnvironment;
import com.betamedia.atom.core.environment.tp.properties.AbstractEnvPropertiesHolder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author mbelyaev
 * @since 6/13/17
 */
@Component
@PropertySource("/env/newAutomation-env.properties")
public class NewAutomationEnvPropertiesHolderImpl extends AbstractEnvPropertiesHolder<NewAutomationEnvironment> implements NewAutomationEnvironment {
}
