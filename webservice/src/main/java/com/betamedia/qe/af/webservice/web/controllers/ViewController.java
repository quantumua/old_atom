package com.betamedia.qe.af.webservice.web.controllers;

import com.betamedia.qe.af.webservice.business.RunTestHandler;
import com.betamedia.qe.af.webservice.storage.StorageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.betamedia.qe.af.webservice.utils.PropertiesUtils.getProperties;

/**
 * Created by mbelyaev on 3/2/17.
 */
@Controller
public class ViewController {
    private static final Logger logger = LogManager
            .getLogger(ViewController.class);

    @Autowired
    private RunTestHandler runTestHandler;

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private StorageService storageService;

    @GetMapping("/")
    public String showUploadForm(Model model) {
        return "uploadForm";
    }

    @PostMapping("/upload/suite")
    public String handleFileUpload(@RequestParam("properties") MultipartFile properties,
                                   @RequestParam("suites[]") MultipartFile[] suites,
                                   @RequestParam("dataSources[]") MultipartFile[] dataSources,
                                   RedirectAttributes redirectAttributes) throws IOException {
        logger.info("Starting tests");
        List<String> suitePaths = Arrays.stream(suites)
                .map(storageService::store)
                .collect(Collectors.toList());

        runTestHandler.handle(getProperties(properties), suitePaths);
        return "redirect:/test-output/index.html";
    }


}
