package com.betamedia.atom.core.fwtestrunner.scheduling;


import java.util.function.Supplier;

/**
 * Created by mbelyaev on 4/19/17.
 */
public class ObservableSupplier<T> implements Supplier<T> {

    private Supplier<T> supplier;
    private ExecutionListener<T> listener;

    public ObservableSupplier(Supplier<T> supplier, ExecutionListener<T> listener) {
        this.supplier = supplier;
        this.listener = listener;
    }

    @Override
    public T get() {
        T results = supplier.get();
        listener.onCompletion(results);
        return results;
    }
}
