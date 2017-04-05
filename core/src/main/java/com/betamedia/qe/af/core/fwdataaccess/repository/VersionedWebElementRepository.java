package com.betamedia.qe.af.core.fwdataaccess.repository;

import com.betamedia.qe.af.core.fwdataaccess.entities.FindBy;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/15/17.
 */
public interface VersionedWebElementRepository {
    FindBy get(String pageObjectName, String locatorId);
}
