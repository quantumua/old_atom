package com.betamedia.atom.app.storage;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author mbelyaev
 * @since 2/28/17
 */
public interface TempStorageService {

    void init();

    String storeToTemp(MultipartFile file, String... pathString);

    void deleteAll();

}
