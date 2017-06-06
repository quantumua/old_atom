package com.betamedia.atom.core.fwdataaccess.repository;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by mbelyaev on 4/27/17.
 */
public interface EntityRepository {

    <T> List<T> get(Class<T> entity);

    void updateExpectedCfdAssets(MultipartFile expectedCfdAssets);
}
