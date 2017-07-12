package com.betamedia.atom.app.reporting;

import java.util.List;

/**
 * @author mbelyaev
 * @since 4/25/17
 */
public interface EmailService {

    void sendLocalFile(String to, String subject, String contentPath, List<String> attachmentPaths);
}
