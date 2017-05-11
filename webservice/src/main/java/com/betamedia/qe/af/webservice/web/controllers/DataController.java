package com.betamedia.qe.af.webservice.web.controllers;

import com.betamedia.qe.af.core.fwdataaccess.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by mbelyaev on 5/10/17.
 */
@RestController
@RequestMapping
public class DataController {
    @Autowired
    private EntityRepository entityRepository;

    @PostMapping("/upload/expectedCfdAssets")
    public ResponseEntity<String> uploadExpectedCfdAssetsCfdAssets(MultipartFile expectedCfdAssets) {
        if (expectedCfdAssets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        entityRepository.updateExpectedCfdAssets(expectedCfdAssets);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
