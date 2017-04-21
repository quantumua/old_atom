package com.betamedia.qe.af.core.fwtestrunner.scheduling;

/**
 * Created by mbelyaev on 4/19/17.
 */
public interface ExecutionListener {
    void onCompletion();

    static ExecutionListener nullListener(){
        return () -> {};
    }
}
