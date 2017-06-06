package com.betamedia.atom.core.fwtestrunner;

/**
 * Created by mbelyaev on 4/25/17.
 */
public class RunnerResult {
    private final String pathToOutput;
    private final boolean hasNotPassed;

    public RunnerResult(String pathToOutput, boolean hasNotPassed) {
        this.pathToOutput = pathToOutput;
        this.hasNotPassed = hasNotPassed;
    }

    public String getPathToOutput() {
        return pathToOutput;
    }

    public boolean hasNotPassed() {
        return hasNotPassed;
    }
}
