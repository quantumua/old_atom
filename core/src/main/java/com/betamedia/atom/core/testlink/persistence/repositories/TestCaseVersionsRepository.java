package com.betamedia.atom.core.testlink.persistence.repositories;


import com.betamedia.atom.core.testlink.persistence.entities.TestLinkTestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Component
public interface TestCaseVersionsRepository extends JpaRepository<TestLinkTestCase, Long> {
    List<TestLinkTestCase> findByExternalIdAndExecutionType(Long externalId, Long executionType);
}

