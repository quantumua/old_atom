package com.betamedia.atom.app.scheduling;

import com.betamedia.atom.app.entity.TestInformation;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author mbelyaev
 * @since 4/20/17
 */
public interface ContinuousTestHandler {

    List<TestInformation> handleTest(String name, Properties properties, MultipartFile[] suites, Optional<String> cronExpression, String emailAddress);

    void stop(UUID id);

    boolean abort(UUID id);

    Set<TestInformation> getInfo();
}
