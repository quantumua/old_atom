package com.betamedia.atom.core.persistence.repositories.impl.testlink;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.betamedia.atom.core.persistence.entities.TestLinkTestCase;

import java.util.List;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Component
public interface TestLinkTestCaseRepository extends JpaRepository<TestLinkTestCase, Long> {
    List<TestLinkTestCase> findByExternalIdAndExecutionType(Long externalId, Long executionType);
}
