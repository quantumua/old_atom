package com.betamedia.atom.core.fwdataaccess.repository.util.version;

import com.betamedia.atom.core.dsl.type.EnvironmentType;

/**
 * @author mbelyaev
 * @since 5/31/17
 */
public interface ApplicationVersionServiceProvider {
    ApplicationVersionService get(EnvironmentType env);
}
