package com.betamedia.atom.webservice.web.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by vsnigur on 6/12/17.
 */
@RestController
public class OutputController {
    private  static final String OUTPUT_FILE = "nohup.out";
    private static final Logger logger = LogManager.getLogger(OutputController.class);

    @RequestMapping("/debug/output")
    public String readOutput() {
        return readFile(OUTPUT_FILE, StandardCharsets.UTF_8);
    }

    /**
     *  read output file from the run folder
     * @param path - output file path
     * @param encoding - encoding for the file
     * @return - string of the file content
     */
    static String readFile(String path, Charset encoding) {
        String output="";
        try {
            output = String.format("<pre>%s</pre>", new String(Files.readAllBytes(Paths.get(path)), encoding));
        } catch (IOException e) {
            logger.info(String.format("Output file %s wasn't found!",OUTPUT_FILE));
        }
        return output;
    }
}
