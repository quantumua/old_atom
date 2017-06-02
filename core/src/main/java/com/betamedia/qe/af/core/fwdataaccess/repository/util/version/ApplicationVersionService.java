package com.betamedia.qe.af.core.fwdataaccess.repository.util.version;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.fwdataaccess.repository.util.RepositoryVersion;

/**
 * @author mbelyaev
 * @since 3/10/17
 */
public interface ApplicationVersionService extends EnvironmentDependent {
    RepositoryVersion getVersion();
}
