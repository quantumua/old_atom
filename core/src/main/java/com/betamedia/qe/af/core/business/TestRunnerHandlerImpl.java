package com.betamedia.qe.af.core.business;

import com.betamedia.qe.af.core.business.types.TestRunnerType;
import com.betamedia.qe.af.core.holder.ConfigurationPropertyKey;
import com.betamedia.qe.af.core.business.runner.TestRunner;
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
        return TestRunnerType.parse((String) properties.get(ConfigurationPropertyKey.TEST_TYPE));
    }
}
