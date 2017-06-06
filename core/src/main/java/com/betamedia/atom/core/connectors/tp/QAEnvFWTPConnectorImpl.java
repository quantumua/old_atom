package com.betamedia.atom.core.connectors.tp;

import com.betamedia.atom.core.environment.tp.QAEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/21/17.
 */
@Component
public class QAEnvFWTPConnectorImpl extends FWTPConnector<QAEnvironment> implements QAEnvironment{
}
