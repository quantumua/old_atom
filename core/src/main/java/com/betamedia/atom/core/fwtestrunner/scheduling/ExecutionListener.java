package com.betamedia.atom.core.fwtestrunner.scheduling;

/**
 * Created by mbelyaev on 4/19/17.
 */
public interface ExecutionListener<T> {
    void onCompletion(T result);

    static ExecutionListener nullListener(){
        return t -> {};
    }
}
