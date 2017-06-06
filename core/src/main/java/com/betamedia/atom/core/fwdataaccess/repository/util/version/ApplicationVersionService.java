package com.betamedia.atom.core.fwdataaccess.repository.util.version;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.fwdataaccess.repository.util.RepositoryVersion;

/**
 * @author mbelyaev
 * @since 3/10/17
 */
public interface ApplicationVersionService extends EnvironmentDependent {
    RepositoryVersion getVersion();
}
