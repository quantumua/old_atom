package com.betamedia.qe.af.webservice.web.controllers;

import com.betamedia.qe.af.webservice.business.ClassLoaderInvocationHandler;
import com.betamedia.qe.af.webservice.business.RunTestHandler;
import com.betamedia.qe.af.webservice.storage.StorageFileNotFoundException;
import com.betamedia.qe.af.webservice.storage.StorageService;
import com.betamedia.qe.af.webservice.web.entities.RunTestParams;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */
@RestController
@RequestMapping
public class RunTestController {

    @Autowired
    private RunTestHandler runTestHandler;

    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ClassLoaderInvocationHandler classLoaderInvocationHandler;

    @GetMapping("/run")
    public ResponseEntity<String> run(@Valid RunTestParams params) {
        try {
            runTestHandler.handle(getProperties(params.getSut()), params.getSuite());
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO better endpoint scheme
    @PostMapping("/upload/tests")
    public ResponseEntity<String> uploadTestJar(MultipartFile jar) throws MalformedURLException {
        if (jar.isEmpty()) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
        String jarPath = storageService.store(jar);
        classLoaderInvocationHandler.setClassLoader(
                new URLClassLoader(
                        new URL[]{Paths.get(jarPath).toUri().toURL()},
                        Thread.currentThread().getContextClassLoader()));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    private Properties getProperties(String fileName) throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = this.getClass().getClassLoader().getResourceAsStream(fileName);
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find " + fileName);
            }
            //load a properties file from class path, inside static method
            prop.load(input);
            return prop;

        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
