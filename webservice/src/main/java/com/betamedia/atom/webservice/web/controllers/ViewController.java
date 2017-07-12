package com.betamedia.atom.webservice.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

import static com.betamedia.atom.app.handler.TestHandler.TEST_OUTPUT_DIRECTORY;

/**
 * Created by mbelyaev on 4/20/17.
 */
@Controller
public class ViewController {

    @RequestMapping("/")
    public String runTests() {
        return "/html/index.html";
    }

    @RequestMapping("/schedule")
    public String scheduleTests() {
        return "/html/scheduler.html";
    }

    @RequestMapping("/upload")
    public String uploadJar() {
        return "/html/uploader.html";
    }

    @RequestMapping("/" + TEST_OUTPUT_DIRECTORY + "**")
    public String redirect(HttpServletRequest request) {
        return "redirect:" + removeDirectory((String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
    }

    private String removeDirectory(String path) {
        return path.replace(TEST_OUTPUT_DIRECTORY, "");
    }
}
