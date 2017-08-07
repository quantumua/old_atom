package com.betamedia.atom.webservice.web.controllers;

import com.betamedia.atom.core.fwdataaccess.repository.CsvResourceRepository;
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
    private CsvResourceRepository csvResourceRepository;

    @PostMapping("/upload/expectedCfdAssets")
    public ResponseEntity<String> uploadExpectedCfdAssetsCfdAssets(MultipartFile expectedCfdAssets) {
        if (expectedCfdAssets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        csvResourceRepository.updateExpectedCfdAssets(expectedCfdAssets);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
