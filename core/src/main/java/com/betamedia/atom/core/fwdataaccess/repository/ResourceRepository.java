package com.betamedia.atom.core.fwdataaccess.repository;

import java.util.List;

/**
 * Created by mbelyaev on 4/18/17.
 */
public interface ResourceRepository {
    List<String> get(String name);

    void store(String name, List<String> content);
}
