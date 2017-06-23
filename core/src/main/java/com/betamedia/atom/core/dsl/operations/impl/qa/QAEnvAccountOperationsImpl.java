package com.betamedia.atom.core.dsl.operations.impl.qa;

import com.betamedia.atom.core.dsl.operations.impl.AbstractAccountOperations;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/21/17.
 */
@Component
public class QAEnvAccountOperationsImpl extends AbstractAccountOperations<QAEnvironment> implements QAEnvironment {
}