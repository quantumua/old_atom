package com.betamedia.qe.af.core.fwtestrunner;

import com.betamedia.qe.af.core.fwtestrunner.runner.TestRunner;
import com.betamedia.qe.af.core.fwtestrunner.types.TestRunnerType;
import com.betamedia.qe.af.core.holders.ConfigurationPropertyKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Service
public class TestRunnerHandlerImpl implements TestRunnerHandler {


    private Map<TestRunnerType, TestRunner> runners;

    @Override
    public List<String> handle(Properties properties, List<String> suitesFileNames) {
        return Optional.ofNullable(runners.get(getType(properties)))
                .orElseThrow(() -> new RuntimeException("No corresponding runner"))
                .run(properties, suitesFileNames);
    }

    @Autowired
    private void setRunners(List<TestRunner> runnersList) {
        this.runners = runnersList.stream()
                .collect(Collectors.toMap(TestRunner::getType, m -> m));
    }

    private TestRunnerType getType(Properties properties) {
        return TestRunnerType.parse((String) properties.get(ConfigurationPropertyKey.RUNNER_TYPE));
    }
}
