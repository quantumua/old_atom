package com.betamedia.qe.af.core.fwtestrunner.reporting;

/**
 * Created by mbelyaev on 4/25/17.
 */
public interface EmailService {
    void sendLocalFile(String to, String subject, String pathToFile);
}
