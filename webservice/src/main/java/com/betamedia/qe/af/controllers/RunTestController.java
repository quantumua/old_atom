package com.betamedia.qe.af.controllers;

import com.betamedia.qe.af.business.RunTestHandler;
import com.betamedia.qe.af.components.SUTPropertiesHolder;
import com.betamedia.qe.af.entities.web.RunTestParams;
import com.betamedia.qe.af.storage.StorageFileNotFoundException;
import com.betamedia.qe.af.storage.StorageService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */
@RestController
@RequestMapping("/run")
public class RunTestController {

    @Autowired
    private RunTestHandler runTestHandler;

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private StorageService storageService;

    @RequestMapping(method = GET)
    public ResponseEntity<String> run(@Valid RunTestParams params) {
        try {
            SUTPropertiesHolder bean = (SUTPropertiesHolder) beanFactory.getBean("scopedTarget.sutPropertiesHolder", getProperties(params.getSut()));
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            requestAttributes.setAttribute("sutPropertyHolder", bean, RequestAttributes.SCOPE_REQUEST);
            RequestContextHolder.setRequestAttributes(requestAttributes, true);
            runTestHandler.handle(params);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("properties") MultipartFile properties,
                                                   @RequestParam("suites[]") MultipartFile[] suites,
                                                   @RequestParam("dataSources[]") MultipartFile[] dataSources,
                                                   RedirectAttributes redirectAttributes) throws IOException {
        try {
            SUTPropertiesHolder bean = (SUTPropertiesHolder) beanFactory.getBean("scopedTarget.sutPropertiesHolder", getProperties(properties));
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            requestAttributes.setAttribute("sutPropertyHolder", bean, RequestAttributes.SCOPE_REQUEST);

            List<String> suitePaths = Arrays.stream(suites)
                    .map(storageService::store)
                    .collect(Collectors.toList());

            Arrays.stream(dataSources)
                    .filter(d -> !d.isEmpty())
                    .forEach(d -> {
                        String path = storageService.store(d);
                        requestAttributes.setAttribute(d.getOriginalFilename(), path, RequestAttributes.SCOPE_REQUEST);
                    });

            RequestContextHolder.setRequestAttributes(requestAttributes, true);

            runTestHandler.handle(new RunTestParams(null, suitePaths, null));
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    private Properties getProperties(MultipartFile uploadedProperties) throws IOException {
        Properties properties = new Properties();
        properties.load(uploadedProperties.getInputStream());
        return properties;
    }
}
