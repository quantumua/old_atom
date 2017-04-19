package com.betamedia.qe.af.core.testingtype.tp;

import com.betamedia.qe.af.core.fwdataaccess.repository.ResourceRepository;
import com.betamedia.qe.af.core.holders.AppContextHolder;

import java.util.List;

/**
 * Created by mbelyaev on 4/18/17.
 */
public class TPResourceAwareClientTest extends TPClientTest {
    private ResourceRepository resourceRepository = null;

    private ResourceRepository getResourceRepository() {
        if (resourceRepository == null) {
            resourceRepository = AppContextHolder.getBean(ResourceRepository.class);
        }
        return resourceRepository;
    }

    public List<String> getContent(String name) {
        return getResourceRepository().get(name);
    }
}
