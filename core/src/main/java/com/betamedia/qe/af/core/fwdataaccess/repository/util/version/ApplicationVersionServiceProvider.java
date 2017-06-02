package com.betamedia.qe.af.core.fwdataaccess.repository.util.version;

import com.betamedia.qe.af.core.dsl.pages.type.EnvironmentType;

/**
 * @author mbelyaev
 * @since 5/31/17
 */
public interface ApplicationVersionServiceProvider {
    ApplicationVersionService get(EnvironmentType env);
}
