package com.betamedia.qe.af.webservice.business;

import com.betamedia.qe.af.common.holder.SUTPropertiesHolder;
import com.betamedia.qe.af.webservice.business.runner.TestRunner;
import com.betamedia.qe.af.webservice.business.types.TestType;
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
public class RunTestHandlerImpl implements RunTestHandler {


    private Map<TestType, TestRunner> runners;

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

    private TestType getType(Properties properties) {
        return TestType.parse((String) properties.get(SUTPropertiesHolder.TEST_TYPE));
    }
}
