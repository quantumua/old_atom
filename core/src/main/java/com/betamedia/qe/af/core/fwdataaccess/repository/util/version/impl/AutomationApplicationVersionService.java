package com.betamedia.qe.af.core.fwdataaccess.repository.util.version.impl;

import com.betamedia.qe.af.core.environment.tp.AutomationEnvironment;
import com.betamedia.qe.af.core.fwdataaccess.repository.util.version.AbstractApplicationVersionService;
import org.springframework.stereotype.Service;

/**
 * @author mbelyaev
 * @since 5/31/17
 */
@Service
public class AutomationApplicationVersionService extends AbstractApplicationVersionService<AutomationEnvironment> implements AutomationEnvironment{
}
