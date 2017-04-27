package com.betamedia.qe.af.core.fwdataaccess.repository;

import java.util.List;

/**
 * Created by mbelyaev on 4/27/17.
 */
public interface EntityRepository {

    <T> List<T> get(Class<T> entity);
}
