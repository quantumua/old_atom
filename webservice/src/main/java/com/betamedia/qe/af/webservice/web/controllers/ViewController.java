package com.betamedia.qe.af.webservice.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
