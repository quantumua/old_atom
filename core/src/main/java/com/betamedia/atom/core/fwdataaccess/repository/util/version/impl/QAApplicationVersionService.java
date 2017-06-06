package com.betamedia.atom.core.fwdataaccess.repository.util.version.impl;

import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.atom.core.fwdataaccess.repository.util.version.AbstractApplicationVersionService;
import org.springframework.stereotype.Service;

/**
 * @author mbelyaev
 * @since 5/31/17
 */
@Service
public class QAApplicationVersionService extends AbstractApplicationVersionService<QAEnvironment> implements QAEnvironment {
}
