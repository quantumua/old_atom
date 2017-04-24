package com.betamedia.qe.af.core.fwtestrunner.scheduling;


/**
 * Created by mbelyaev on 4/19/17.
 */
public class ObservableRunnable implements Runnable {

    private Runnable runnable;
    private ExecutionListener listener;

    public ObservableRunnable(Runnable runnable, ExecutionListener listener) {
        this.runnable = runnable;
        this.listener = listener;
    }

    @Override
    public void run() {
        runnable.run();
        listener.onCompletion();
    }
}
