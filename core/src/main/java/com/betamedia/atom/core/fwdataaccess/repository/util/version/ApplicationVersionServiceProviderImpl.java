package com.betamedia.atom.core.fwdataaccess.repository.util.version;

import com.betamedia.atom.core.dsl.type.EnvironmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author mbelyaev
 * @since 5/31/17
 */
@Component
public class ApplicationVersionServiceProviderImpl implements ApplicationVersionServiceProvider {
    private Map<EnvironmentType, ApplicationVersionService> holders;

    @Override
    public ApplicationVersionService get(EnvironmentType env) {
        return holders.get(env);
    }

    @Autowired(required = false)
    private void init(List<ApplicationVersionService> holders) {
        this.holders = holders.stream().collect(Collectors.toMap(ApplicationVersionService::getEnvironment, Function.identity()));
    }
}
