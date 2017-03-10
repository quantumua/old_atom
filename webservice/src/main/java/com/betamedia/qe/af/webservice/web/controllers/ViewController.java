package com.betamedia.qe.af.webservice.web.controllers;

import com.betamedia.qe.af.common.holder.SUTPropertiesHolder;
import com.betamedia.qe.af.webservice.business.RunTestHandler;
import com.betamedia.qe.af.webservice.storage.StorageService;
import com.betamedia.qe.af.webservice.web.entities.RunTestParams;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by mbelyaev on 3/2/17.
 */
@Controller
public class ViewController {
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

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("properties") MultipartFile properties,
                                   @RequestParam("suites[]") MultipartFile[] suites,
                                   @RequestParam("dataSources[]") MultipartFile[] dataSources,
                                   RedirectAttributes redirectAttributes) throws IOException {
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
        return "redirect:/test-output/index.html";
    }

    private Properties getProperties(MultipartFile uploadedProperties) throws IOException {
        Properties properties = new Properties();
        properties.load(uploadedProperties.getInputStream());
        return properties;
    }
}
