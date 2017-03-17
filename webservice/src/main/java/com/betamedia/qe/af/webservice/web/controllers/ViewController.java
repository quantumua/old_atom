package com.betamedia.qe.af.webservice.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by mbelyaev on 3/2/17.
 */
@Controller
public class ViewController {
    @GetMapping("/")
    public String showUploadForm(Model model) {
        return "uploadForm";
    }

}
