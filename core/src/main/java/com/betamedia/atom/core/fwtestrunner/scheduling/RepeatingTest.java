package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.task.TaskExecutor;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


/**
 * @author mbelyaev
 * @since 6/16/17
 */
public class RepeatingTest extends ContinuousTest {
    private static final Logger logger = LogManager.getLogger(RepeatingTest.class);
    private final TaskExecutor executor;

    public RepeatingTest(TestInformation testInformation, Function<Consumer<List<TestInformation>>, List<TestInformation>> testExecution, Consumer<TestInformation> onTestSubmitCompletion, TaskExecutor executor) {
        super(testInformation, testExecution, onTestSubmitCompletion);
        this.executor = executor;
    }

    @Override
    public void start() {
        executor.execute(this::runExecution);
    }

    @Override
    public void stop() {
        logger.info("Setting test termination flag.", this);
        isEnabled.set(false);
    }

    @Override
    protected void onIterationCompletion(List<TestInformation> iterationResults) {
        logger.info("Finished executing test iteration.", this);
        if (isEnabled.get()) {
            logger.info("Restarting continuous test.", this);
            start();
            logger.info("Continuous test restarted.", this);
            return;
        }
        logger.info("The test will no longer be restarted.", this);
        testInformation = testInformation.update().withStatus(TestInformation.Status.COMPLETED).build();
        onTestSubmitCompletion.accept(this.testInformation);
    }

}
