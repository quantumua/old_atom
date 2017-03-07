package com.betamedia.qe.af.core.pages;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public interface PageOperation<T> extends Location {
    T goTo();
}
