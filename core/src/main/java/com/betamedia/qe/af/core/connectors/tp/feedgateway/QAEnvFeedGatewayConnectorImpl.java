package com.betamedia.qe.af.core.connectors.tp.feedgateway;

import com.betamedia.qe.af.core.environment.tp.QAEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/21/17.
 */
@Component
public class QAEnvFeedGatewayConnectorImpl extends FWFeedGatewayConnector<QAEnvironment> implements QAEnvironment {
}
