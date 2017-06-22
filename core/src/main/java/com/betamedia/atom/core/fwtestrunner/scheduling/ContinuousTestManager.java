package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestInformation;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author mbelyaev
 * @since 4/20/17
 */
public interface ContinuousTestManager {

    TestInformation createTest(String name, String emailAddress, Properties properties, MultipartFile[] suites, Optional<String> cronExpression);

    void stopTest(UUID name);

    Set<TestInformation> getInfo();
}
