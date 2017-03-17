package com.betamedia.qe.af.webservice.business;

import net.sf.cglib.proxy.InvocationHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by mbelyaev on 3/13/17.
 */
@Component
public class ClassLoaderInvocationHandler implements InvocationHandler {
    private ClassLoader classLoader;

    //TODO concurrency
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(classLoader, args);
    }
}
