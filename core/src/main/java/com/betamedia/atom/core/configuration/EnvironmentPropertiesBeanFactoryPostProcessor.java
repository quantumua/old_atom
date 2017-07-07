package com.betamedia.atom.core.configuration;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;


/**
 * This post-processor looks for any beans that extend EnvironmentDependent and selects the first match to determine the
 * primary environment for application. All bean definitions with prefix matching selected environment will be marked as
 * {@link org.springframework.context.annotation.Primary} to satisfy JPA transaction manager and stub configuration.
 *
 * @author mbelyaev
 * @since 7/3/17
 */
@Service
public class EnvironmentPropertiesBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static final Logger logger = LogManager.getLogger(EnvironmentPropertiesBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
        String[] environmentDependentBeans = bf.getBeanNamesForType(EnvironmentDependent.class);
        if (environmentDependentBeans.length < 1) return;
        //TODO improve environment prefix matching
        String primaryEnvironment = environmentDependentBeans[0].split("(?=\\p{Upper})")[0];
        logger.info("Selected " + primaryEnvironment + " as primary environment");
        bf.getBeanNamesIterator().forEachRemaining(name -> {
            if (name.startsWith(primaryEnvironment))
                bf.getBeanDefinition(name).setPrimary(true);
        });
        logger.info("Finished  marking beans with  '" + primaryEnvironment + "' prefix as primary");
    }

}
