package com.betamedia.atom.core.fwtestrunner;

import java.util.List;

/**
 * @author mbelyaev
 * @since 4/25/17
 */
public class RunnerResult {
    private final String outputPath;
    private final boolean hasNotPassed;
    private final List<String> attachmentPaths;

    public RunnerResult(boolean hasNotPassed, String outputPath, List<String> attachmentPaths) {
        this.hasNotPassed = hasNotPassed;
        this.outputPath = outputPath;
        this.attachmentPaths = attachmentPaths;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public boolean hasNotPassed() {
        return hasNotPassed;
    }

    public List<String> getAttachmentPaths() {
        return attachmentPaths;
    }
}
