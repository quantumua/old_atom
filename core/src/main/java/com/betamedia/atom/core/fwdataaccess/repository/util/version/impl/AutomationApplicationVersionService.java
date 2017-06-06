package com.betamedia.atom.core.fwdataaccess.repository.util.version.impl;

import com.betamedia.atom.core.fwdataaccess.repository.util.version.AbstractApplicationVersionService;
import com.betamedia.atom.core.environment.tp.AutomationEnvironment;
import org.springframework.stereotype.Service;

/**
 * @author mbelyaev
 * @since 5/31/17
 */
@Service
public class AutomationApplicationVersionService extends AbstractApplicationVersionService<AutomationEnvironment> implements AutomationEnvironment{
}
