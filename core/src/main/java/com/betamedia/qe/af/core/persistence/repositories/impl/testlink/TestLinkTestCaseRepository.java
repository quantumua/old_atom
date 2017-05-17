package com.betamedia.qe.af.core.persistence.repositories.impl.testlink;

import com.betamedia.qe.af.core.persistence.entities.TestLinkTestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Component
public interface TestLinkTestCaseRepository extends JpaRepository<TestLinkTestCase, Long> {
    List<TestLinkTestCase> findByExternalIdAndExecutionType(Long externalId, Long executionType);
}
